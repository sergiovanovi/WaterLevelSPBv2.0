package com.sergiovanovi.model;

import com.sergiovanovi.model.enums.Role;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.util.CollectionUtils;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;
import java.util.EnumSet;
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

    @Email
    @NotBlank
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @NotBlank
    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "enabled", columnDefinition = "bool default true")
    private boolean enabled;

    @Column(name = "min", nullable = false)
    private double min;

    @Column(name = "max", nullable = false)
    private double max;

    //util == 0 - within normal meters, util > 0 (default 1)- above the norm meters, util < 0 (default -1)- less than the norm meters
    @Column(name = "util", nullable = false)
    private int util;

    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "id"))
    @Column(name = "role")
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<Role> roles;

    public User() {
    }

    public User(String email, String password, int min, int max, Set<Role> roles) {
        this.email = email;
        this.password = password;
        this.enabled = false;
        this.min = min;
        this.max = max;
        this.util = 0;
        this.roles = roles;
    }

    public int getId() {
        return id;
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

    public double getMin() {
        return min;
    }

    public double getMax() {
        return max;
    }

    public int getUtil() {
        return util;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setId(int id) {
        this.id = id;
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

    public void setMin(double min) {
        this.min = min;
    }

    public void setMax(double max) {
        this.max = max;
    }

    public void setUtil(int util) {
        this.util = util;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = CollectionUtils.isEmpty(roles) ? Collections.emptySet() : EnumSet.copyOf(roles);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", enabled=" + enabled +
                ", min=" + min +
                ", max=" + max +
                ", util=" + util +
                ", roles=" + roles +
                '}';
    }
}
