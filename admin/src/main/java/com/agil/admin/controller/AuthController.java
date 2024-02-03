package com.agil.admin.controller;

import java.util.*;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import com.agil.admin.model.*;
import com.agil.admin.payload.response.LoginResponse;
import com.agil.admin.payload.response.MessageResponse;
import com.agil.admin.payload.resuest.LoginRequest;
import com.agil.admin.repository.AppointmentRepository;
import com.agil.admin.repository.CounterAgentRepository;
import com.agil.admin.repository.RoleRepository;
import com.agil.admin.repository.UserRepository;
import com.agil.admin.security.jwt.JwtUtils;
import com.agil.admin.security.services.UserDetailsImpl;
import com.agil.admin.service.ProductService;
import com.agil.admin.service.UserService;
import io.jsonwebtoken.MalformedJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;
    @Autowired
    CounterAgentRepository counterAgentRepository;
    @Autowired
    ProductService productService;
    @Autowired
    AppointmentRepository appointmentRepository;
    @Autowired
    PasswordEncoder encoder;
    @Autowired
    JwtUtils jwtUtils;
    @Autowired
    private UserService userService;


    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt=jwtUtils.generateJwtToken(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());
        /*Map<String, Object> response = new HashMap<>();
        response.put("token", jwt);
        response.put("user", userDetails);*/
        String email=userDetails.getEmail();
        return ResponseEntity.ok()
            .body(new LoginResponse(
                jwt,
                "Login successfull",
                true,
                    "Bearer", (long) (jwtUtils.getJwtExpirationMs() / 1000),
                    roles,
                    email
            ));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody User userRequest) {
        if (userRepository.existsByEmail(userRequest.getEmail())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Email is already in use!"));
        }
        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                .orElseThrow(() ->  new RuntimeException("Role is not found."));
        Set<Role> roles = new HashSet<>();
        roles.add(userRole);
        //Set<Role> roleInputSet=userRequest.getRoles();
        /*roleInputSet.forEach(input-> {
            Role userRole = null;
            if (input.getName().name() !=null) {
                userRole = roleRepository.findByName(input.getName()).orElseThrow(() -> new RuntimeException("Role is not found."));
                roles.add(userRole);
            }
            if (input.getName().name() ==null) {
                 userRole = roleRepository.findByName(ERole.ROLE_USER)
                        .orElseThrow(() ->  new RuntimeException("Role is not found."));                roles.add(userRole);
            }
            *//*if (input.getName().name() == "ROLE_MODERATOR"){
                userRole = roleRepository.findByName(input.getName()).orElseThrow(() -> new RuntimeException("Role is not found."));
                roles.add(userRole);
            }*//*
        } );*/
        /*roleInputSet.forEach(input->{
            if (input.getName().name()=="ROLE_ADMIN")
            {
                roleRepository.findByName(input.getName());
            }roles.add(input);
        }
        );*/

        // set a default role in the list of roles
        /*Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                .orElseThrow(() ->  new RuntimeException("Role is not found."));
        Set<Role> roles = new HashSet<>();
        roles.add(userRole);*/

        // Add a default role to user
        String pass=userRequest.getPassword();
        userRequest.setPassword(encoder.encode(userRequest.getPassword()));
        userRequest.setRoles(roles);



        // Save user in repository
        userRepository.save(userRequest);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));

    }

    @PostMapping("/signup/counterAgent")
    @PreAuthorize("hasRole('ROLE_ADMIN') ")
    public ResponseEntity<?> registerCounterAgent(@Valid @RequestBody CounterAgent counterAgentRequest) {
        if (userRepository.existsByEmail(counterAgentRequest.getUser().getEmail())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Email is already in use!"));
        }

        Product currentProduct =productService.findById(counterAgentRequest.getProduct().getId());
        counterAgentRequest.setProduct(currentProduct);
        Role userRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
                .orElseThrow(() ->  new RuntimeException("Role is not found."));
        Set<Role> roles = new HashSet<>();
        roles.add(userRole);
        /*Set<Role> roles = new HashSet<>();
        Set<Role> roleInputSet=counterAgentRequest.getUser().getRoles();
        roleInputSet.forEach(input-> {
            Role userRole = null;
            if (input.getName().name().equals("ROLE_MODERATOR")){
                userRole = roleRepository.findByName(input.getName()).orElseThrow(() -> new RuntimeException("Role is not found."));
                roles.add(userRole);
            }
        } );*/
        // Add a default role to user
         String pass = counterAgentRequest.getUser().getPassword();
        counterAgentRequest.getUser().setPassword(encoder.encode(counterAgentRequest.getUser().getPassword()));
        counterAgentRequest.getUser().setRoles(roles);

        //counterAgentService.createCounterAgent(counterAgentRequest);
        counterAgentRepository.save(counterAgentRequest);
        // Save user in repository
        userRepository.save(counterAgentRequest.getUser());

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));

    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request) {
        String authToken = request.getHeader("Authorization");
        if (StringUtils.hasText(authToken) && authToken.startsWith("Bearer ")) {
            String jwtToken = authToken.substring(7);
            try {
                jwtUtils.invalidateToken(jwtToken);
                // Add token's unique identifier to blacklist
                // or revoked token list
                return ResponseEntity.ok("Logout successful");
            } catch (MalformedJwtException ex) {
                return ResponseEntity.badRequest().body("Invalid JWT token");
            }
        } else {
            return ResponseEntity.badRequest().body("Authorization header not found");
        }
    }

    @GetMapping("/allUsers")
    @PreAuthorize(" hasRole('ROLE_ADMIN') ")
    public ResponseEntity<?> userList() {
        List<User> users = userRepository.findAll();
        return ResponseEntity.ok().body(users);
    }
    @GetMapping("/currentUser")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<User> getCurrentUser(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        return ResponseEntity.ok().body(user);
    }

    @GetMapping("user/{id}")
    @PreAuthorize(" hasRole('ROLE_MODERATOR') or hasRole('ROLE_ADMIN') ")
    ResponseEntity<?> findById(@PathVariable("id") Long id){
        Optional user = userRepository.findById(id);
        return ResponseEntity.ok().body(user);
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN') ")
    public ResponseEntity<?> deleteUserById(@PathVariable("id") Long id) {
        Optional<User> user = userRepository.findById(id);
            appointmentRepository.deleteByUserId(id);
            
        if (user.isPresent()) {
            userRepository.deleteById(user.get().getId());
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }

    }
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN') ")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
        User updatedUser = userService.updateUser(id, user);
        if (updatedUser != null) {
            return ResponseEntity.ok(updatedUser);
        } else {
            return ResponseEntity.notFound().build();
        }
    }



}

