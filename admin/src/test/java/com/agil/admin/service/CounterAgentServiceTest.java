package com.agil.admin.service;

import com.agil.admin.model.*;
import com.agil.admin.repository.CounterAgentRepository;
import com.agil.admin.repository.ProductRepository;
import com.agil.admin.repository.RoleRepository;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import static com.agil.admin.model.ERole.ROLE_MODERATOR;
import static com.agil.admin.model.ERole.ROLE_USER;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CounterAgentServiceTest {

    @Autowired
    private UserService userService;
    @Autowired
    private ProductService productService;
    @Autowired
    private AppointmentService service;
    @Autowired
    private CounterAgentService counterAgentService;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private CounterAgentRepository counterAgentRepository;
    @Autowired
    private ProductRepository productRepository;

    @Test
    public void shouldUpdateAppointmentStatusWithSuccess(){
        Role roleUser=new Role(ROLE_USER);
        Set<Role> roleSet=new HashSet<>();
        roleSet.add(roleUser);

        User user=new User(null,"Flen","ben Foulen","29111222","w@gmail.com","AA",null);
        userService.createUser(user);
        //user.setRoles(roleSet);
        LocalDate date = LocalDate.now();
        Appointment expectedAppointment =new Appointment(null,date,"14","20","gabes", EStatus.PENDING,null,null);
        service.createAppointment(expectedAppointment);
        expectedAppointment.setUser(user);
        service.updateAppointmentStatus(expectedAppointment.getId(),"CONFIRMED");

        Appointment savedAppointment=service.createAppointment(expectedAppointment);
        assertNotNull(savedAppointment);
        assertNotNull(savedAppointment.getId());
        Assertions.assertEquals(expectedAppointment.getDate(),savedAppointment.getDate());
        Assertions.assertEquals(expectedAppointment.getUser().getId(),savedAppointment.getUser().getId());
    }





}