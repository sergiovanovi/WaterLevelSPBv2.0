package com.sergiovanovi.model;

import com.sergiovanovi.model.enums.Role;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.annotation.Id;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(schema = "sys", name = "users")
public class User {

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private int id;

    @NotBlank
    @Column(name = "name")
    private String name;

    @Email
    @NotBlank
    @Column(name = "email", unique = true)
    private String email;

    @NotBlank
    @Column(name = "password")
    private String password;

    @Column(name = "enabled")
    private boolean enabled;

    @Column(name = "min")
    private int min;

    @Column(name = "max")
    private int max;

    @Column(name = "message")
    private String message;

    private Set<Role> roles;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", enabled=" + enabled +
                ", min=" + min +
                ", max=" + max +
                ", message='" + message + '\'' +
                ", roles=" + roles +
                '}';
    }
}
