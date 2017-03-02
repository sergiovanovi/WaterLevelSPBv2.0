package com.sergiovanovi.model;

import org.springframework.data.annotation.Id;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(schema = "sys", name = "meters")
public class Meter {

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "level")
    private int level;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @Column(name = "date_time")
    private LocalDateTime dateTime;

    @Override
    public String toString() {
        return "Meter{" +
                "id=" + id +
                ", level=" + level +
                ", dateTime=" + dateTime +
                '}';
    }
}
