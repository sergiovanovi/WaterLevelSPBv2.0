package com.sergiovanovi.model;

import com.sergiovanovi.model.enums.Role;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import java.util.Collections;
import java.util.Set;

@Entity
@Access(AccessType.FIELD)
@Table(schema = "sys", name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Access(value = AccessType.PROPERTY)
    @Column(name = "id", nullable = false)
    private int id;

    @NotBlank
    @Column(name = "name")
    private String name;

    @Email
    @NotBlank
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @NotBlank
    @Column(name = "password")
    private String password;

    @Column(name = "enabled", columnDefinition = "bool default true")
    private boolean enabled;

    @Column(name = "min")
    private int min;

    @Column(name = "max")
    private int max;

    @Column(name = "message")
    private String message;

    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "email"))
    @Column(name = "role")
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<Role> roles;

    public User() {
    }

    public User(String email, String password, int min, int max, Set<Role> roles) {
        this.name = "";
        this.email = email;
        this.password = password;
        this.enabled = true;
        this.min = min;
        this.max = max;
        this.message = "";
        this.roles = roles;
    }

    public User(String name, String email, String password, int min, int max, String message) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.enabled = true;
        this.min = min;
        this.max = max;
        this.message = message;
        this.roles = Collections.emptySet();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }

    public String getMessage() {
        return message;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

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
