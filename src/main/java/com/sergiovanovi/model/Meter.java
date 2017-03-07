package com.sergiovanovi.model;


import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Access(AccessType.FIELD)
@Table(schema = "sys", name = "meters")
public class Meter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Access(value = AccessType.PROPERTY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "level")
    private double level;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @Column(name = "date_time", columnDefinition = "timestamp default now()")
    private LocalDateTime dateTime;

    public Meter() {
    }

    public Meter(int level) {
        this.level = level;
    }

    public Meter(int level, LocalDateTime dateTime) {
        this.level = level;
        this.dateTime = dateTime;
    }

    public int getId() {
        return id;
    }

    public double getLevel() {
        return level;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLevel(double level) {
        this.level = level;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return "Meter{" +
                "id=" + id +
                ", level=" + level +
                ", dateTime=" + dateTime.toString().replace("T", " ");
    }
}
