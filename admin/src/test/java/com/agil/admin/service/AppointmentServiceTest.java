package com.agil.admin.service;

import com.agil.admin.model.*;
import com.agil.admin.repository.RoleRepository;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.security.Timestamp;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static com.agil.admin.model.ERole.ROLE_USER;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AppointmentServiceTest {
    @Autowired
    private AppointmentService service;

    @Autowired
    private UserService userService;
    @Test
    public void shouldSaveAppointmentWithSuccess(){
        Role roleUser=new Role(ROLE_USER);
        Set<Role> roleSet=new HashSet   <>();
        roleSet.add(roleUser);

        User user=new User(null,"Flen","ben Foulen","29111222","zz@gmail.com","AA",null);
        userService.createUser(user);
        //user.setRoles(roleSet);
        LocalDate date = LocalDate.now();
        Appointment expectedAppointment =new Appointment(null,date,"14","20","gabes", EStatus.PENDING,null,null);
        service.createAppointment(expectedAppointment);
        expectedAppointment.setUser(user);


        Appointment savedAppointment=service.createAppointment(expectedAppointment);
        assertNotNull(savedAppointment);
        assertNotNull(savedAppointment.getId());
        Assertions.assertEquals(expectedAppointment.getDate(),savedAppointment.getDate());
        Assertions.assertEquals(expectedAppointment.getUser().getId(),savedAppointment.getUser().getId());
    }
    @Test
    public void shouldSignUpWithSuccess(){
        Role roleUser=new Role(ROLE_USER);
        Set<Role> roleSet=new HashSet<>();
        roleSet.add(roleUser);

        User expectedUser=new User(null,"Flen","ben Foulen","29111222","foulen@gmail.com","AA",null);
        userService.createUser(expectedUser);
        //user.setRoles(roleSet);
        User savedUser=userService.createUser(expectedUser);
        assertNotNull(savedUser);
        assertNotNull(savedUser.getId());
        Assertions.assertEquals(expectedUser.getRoles(),expectedUser.getRoles());
    }



}