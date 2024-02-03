package com.agil.admin.service;

import com.agil.admin.model.Appointment;
import com.agil.admin.model.Role;
import com.agil.admin.model.User;
import com.agil.admin.repository.RoleRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.agil.admin.model.ERole.ROLE_ADMIN;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AdminServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private AppointmentService service;
    @Autowired
    private RoleRepository roleRepository;

    @Test
    public void shouldGetAllAppointmentsWithSuccess(){
        Role roleUser=roleRepository.findByName(ROLE_ADMIN).orElseThrow(() ->  new RuntimeException("Role is not found."));
        Set<Role> roleSet=new HashSet<>();
        roleSet.add(roleUser);
        User admin=new User(null,"admin","ben Foulen","29111222","adminn@agil.com","admin",roleSet);
        userService.createUser(admin);
        //user.setRoles(roleSet);
        List<Appointment> expectedAppointments =service.getAllAppointments();
        assertNotNull(expectedAppointments);
    }


}