package com.gudangdamar.main.model;

import jakarta.persistence.*;

@Entity
public class User {

    // Kolom id sebagai primary key
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Kolom name
    private String name;
    // Kolom email, harus ada sesuai dengan yang dibutuhkan
    private String email;
    // Getter dan Setter untuk id
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    // Getter dan Setter untuk name
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Getter dan Setter untuk email
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
