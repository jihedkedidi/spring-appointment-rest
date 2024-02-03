package com.agil.admin.controller;

import com.agil.admin.model.Appointment;
import com.agil.admin.model.EStatus;
import com.agil.admin.model.Product;
import com.agil.admin.model.User;
import com.agil.admin.repository.AppointmentRepository;
import com.agil.admin.service.AppointmentService;
import com.agil.admin.service.ProductService;
import com.agil.admin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
@RestController
@RequestMapping("api/appointment/")
public class AppointmentController {
    @Autowired
    private AppointmentRepository  appointmentRepository;

    @Autowired
    private UserService userService;
    @Autowired
    private ProductService productService;

    @Autowired
    private AppointmentService appointmentService;

    // GET /appointments: Retrieves a list of all appointments
    @GetMapping("")
    public List<Appointment> getAllAppointments() {
        return appointmentService.getAllAppointments();
    }

    // GET /appointments/{id}: Retrieves a single appointment by its ID
    @GetMapping("/{id}")
    public Appointment getAppointmentById(@PathVariable(value = "id") Long appointmentId) {
        return appointmentService.getAppointmentById(appointmentId);
    }

    @GetMapping("/getAppointmentsWithMatchingCounterAgent/{id}")
    public List<Appointment> getAppointmentsWithMatchingCounterAgents(@PathVariable  (value = "id") Long appointmentId){
        return appointmentService.getAppointmentsWithMatchingCounterAgents(appointmentId);
    }

    @PostMapping("/create")
    @PreAuthorize("hasRole('USER') or hasRole('ROLE_MODERATOR') or hasRole('ROLE_ADMIN') ")
    public Appointment createAppointment(@RequestBody Appointment appointment) {

        User currentUser=userService.getUserById(appointment.getUser().getId());
        appointment.setUser(currentUser);
        appointment.setStatus(EStatus.PENDING);
        Product currentProduct =productService.findById(appointment.getProduct().getId());
        appointment.setProduct(currentProduct);
        return appointmentService.createAppointment(appointment);
    }
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ROLE_MODERATOR') or hasRole('ROLE_ADMIN')")
    public Appointment updateAppointment(@PathVariable  (value = "id") Long appointmentId,@RequestBody Appointment appointment){

        User currentUser = userService.getUserById(appointment.getUser().getId());
        appointment.setUser(currentUser);

        Product currentProduct = productService.findById(appointment.getProduct().getId());
        appointment.setProduct(currentProduct);
        return appointmentService.updateAppointment(appointmentId,appointment);
    }
    // DELETE /appointments/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Appointment> deleteAppointment(@PathVariable("id") Long id) {
        Optional<Appointment> appointment = appointmentRepository.findById(id);
        if (appointment.isPresent()) {
            appointmentRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping("update/status/{id}")
    public void updateAppointmentStatus(@PathVariable  (value = "id") Long appointmentId,@RequestBody String status){
        appointmentService.updateAppointmentStatus(appointmentId,status);
    }
    @GetMapping("/getAppointmentsWithMatchingUser/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ROLE_MODERATOR') or hasRole('ROLE_ADMIN') ")
    public List<Appointment> getAppointmentsWithMatchingUser(@PathVariable (value="id") Long userId){
        return appointmentService.getAppointmentWithMatchingUser(userId);
    }

    @GetMapping("/getAppointmentWithStatusPending")
    public List<Appointment> getAppointmentWithStatusPending(){
        return appointmentService.getAppointmentsWithStatusPending();
    }

    @GetMapping("getAppointmentsWithMatchingDateLocProd/{date}")
    @PreAuthorize("hasRole('USER') or hasRole('ROLE_MODERATOR') or hasRole('ROLE_ADMIN')")
    public List<Appointment> getAppointmentsWithMatchingDateLocProd(@PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date ) {
        return appointmentService.getAppointmentsWithMatchingDateLocProd(date);
    }


}









