package com.sergiovanovi.model;

import com.sergiovanovi.model.enums.Role;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.annotation.Id;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "USERS")
public class User {

    @Id
    @GeneratedValue
    @Column(name = "ID", nullable = false)
    private int id;

    @NotBlank
    @Column(name = "NAME")
    private String name;

    @Email
    @NotBlank
    @Column(name = "EMAIL", unique = true)
    private String email;

    @NotBlank
    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @Column(name = "ENABLED")
    private boolean enabled;

    private Set<Role> roles;

}
