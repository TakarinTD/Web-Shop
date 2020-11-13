package com.example.demo.entity;

import javax.persistence.*;

@Entity
@Table (name = "contact")
public class Contact {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (name = "guest_name")
    private String guestName;

    @Column (name = "email")
    private String emal;

    @Column (name = "subject")
    private String subject;

    @Column (name = "message")
    private String message;

    public Contact () {
    }

    public Long getId () {
        return id;
    }

    public void setId (Long id) {
        this.id = id;
    }

    public String getGuestName () {
        return guestName;
    }

    public void setGuestName (String guestName) {
        this.guestName = guestName;
    }

    public String getEmal () {
        return emal;
    }

    public void setEmal (String emal) {
        this.emal = emal;
    }

    public String getSubject () {
        return subject;
    }

    public void setSubject (String subject) {
        this.subject = subject;
    }

    public String getMessage () {
        return message;
    }

    public void setMessage (String message) {
        this.message = message;
    }
}
