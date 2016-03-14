package com.theironyard.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by alexanderhughes on 3/14/16.
 */
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue
    int id;
    @NotNull
    String name;

    public User(String name) {
        this.name = name;
    }

    public User() {
    }
}
