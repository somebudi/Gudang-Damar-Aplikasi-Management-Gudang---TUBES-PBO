package com.gudangdamar.main.model;

import jakarta.persistence.*;

@Entity
@Table(name = "login")  // Mengubah nama tabel menjadi "users" (lebih umum)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String role;

    // Constructor
    public User(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    // Default constructor (required for JPA)
    public User() {}

    // Getter dan Setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        if (username == null || username.isEmpty()) {
            System.out.println("Username tidak boleh kosong");
        } else {
            this.username = username;
        }
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    // Method untuk login (bisa dihapus jika tidak diperlukan di model)
    public boolean login(String username, String password) {
        return this.username.equals(username) && this.password.equals(password);
    }
}
