package com.theironyard.entities;

import javax.persistence.*;

/**
 * Created by alexanderhughes on 3/14/16.
 */
@Entity
@Table(name = "favorites")
public class Favorite {
    @Id
    @GeneratedValue
    int id;

    @ManyToOne
    Event event;
    @ManyToOne
    User user;

    public Favorite() {
    }

    public Favorite(Event event, User user) {
        this.event = event;
        this.user = user;
    }
}
