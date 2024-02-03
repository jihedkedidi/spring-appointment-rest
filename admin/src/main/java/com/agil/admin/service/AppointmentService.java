package com.agil.admin.service;

import com.agil.admin.model.Appointment;
import com.agil.admin.model.EStatus;
import com.agil.admin.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService {

    @Autowired
    AppointmentRepository appointmentRepository;

    public List<Appointment> getAppointmentsWithMatchingCounterAgents(Long agentId) {
        return appointmentRepository.getAppointmentsWithMatchingCounterAgents(agentId);
    }

    public List<Appointment> getAppointmentWithMatchingUser(Long userId) {
        return appointmentRepository.getAppointmentsWithMatchingUser(userId);
    }

    public Appointment createAppointment(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }

    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    public Appointment getAppointmentById(Long id) {
        Optional<Appointment> appointmentOptional = appointmentRepository.findById(id);
        if (appointmentOptional.isPresent()) {
            return appointmentOptional.get();
        } else {
            throw new RuntimeException("Appointment Not Found with this id" + id);
        }
    }

    public Appointment updateAppointment(Long id, Appointment appointment) {
        Optional<Appointment> appointmentOptional = appointmentRepository.findById(id);
        if (appointmentOptional.isPresent()) {
            appointment.setId(id);
            return appointmentRepository.save(appointment);
        } else {
            throw new RuntimeException("Appointment Not Found with this id " + id);
        }
    }

    public void deleteAppointment(Long id) {
        appointmentRepository.deleteById(id);
    }

    public void updateAppointmentStatus(Long id, String status) {
        switch (status){
            case "CONFIRMED":
                Optional<Appointment> appointment = appointmentRepository.findById(id);
                appointment.ifPresent(app -> {app.setStatus(EStatus.CONFIRMED);
                    appointmentRepository.save(app);
                });
                break;
            case "CANCELED" :
                Optional<Appointment> appointmentt = appointmentRepository.findById(id);
                appointmentt.ifPresent(app -> {app.setStatus(EStatus.CANCELED);
                    appointmentRepository.save(app);
                });
            break;
            default : Optional<Appointment> appointmenttt = appointmentRepository.findById(id);
                appointmenttt.ifPresent(app -> {app.setStatus(EStatus.CANCELED);
                    appointmentRepository.save(app);
                });
        }
    }

    public List<Appointment> getAppointmentsWithStatusPending(){
        return appointmentRepository.getAppointmentsWithStatusPending(EStatus.PENDING);
    }

    public  List<Appointment> getAppointmentsWithMatchingDateLocProd(LocalDate date){
        return appointmentRepository.getAppointmentsWithMatchingDateLocProd(date);
    }
}

