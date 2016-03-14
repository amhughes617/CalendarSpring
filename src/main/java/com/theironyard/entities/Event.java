package com.theironyard.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * Created by alexanderhughes on 3/14/16.
 */
@Entity
@Table(name = "events")
public class Event {
    @Id
    @GeneratedValue
    private int id;
    @NotNull
    private String description;
    @NotNull
    private LocalDateTime dateTime;
    @ManyToOne
    User user;

    public Event() {
    }

    public Event(String description, LocalDateTime dateTime , User user) {
        this.description = description;
        this.dateTime = dateTime;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
}
