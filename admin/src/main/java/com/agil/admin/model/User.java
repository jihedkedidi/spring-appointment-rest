package com.agil.admin.model;

import javax.persistence.*;
import javax.transaction.Transactional;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
@Entity
@Table(name = "_users",uniqueConstraints = {
        @UniqueConstraint(columnNames = "email")
})
@Transactional
    public class User {
            @Id
            @GeneratedValue(strategy = GenerationType.IDENTITY)
            private Long id;

            @Column(name = "firstName")
            private String firstName;

            @Column(name = "lastName")
            private String lastName;

            @Column(name = "phone")
            private String phone;

            @Column(name = "email")
            private String email;

            @Column(name = "password")
            private String password;

            @ManyToMany(fetch = FetchType.LAZY)
            @JoinTable(name = "user_roles",
                    joinColumns = @JoinColumn(name = "user_id"),
                    inverseJoinColumns = @JoinColumn(name = "role_id"))
            private Set<Role> roles = new HashSet<>();

            public User(){}

    public User( Long id,String firstName, String lastName, String phone, String email, String password,Set<Role>  roles) {

        this.id=id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }

    public User(String email) {
        this.email = email;

    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }


}
