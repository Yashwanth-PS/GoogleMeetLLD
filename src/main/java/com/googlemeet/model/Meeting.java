package com.googlemeet.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Meeting {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int meetingId;
    private String name;
    private String password;
    private long startTime;
    private long endTime;
    @OneToMany
    private List<User> attendees;
}