package com.gudangdamar.main.model;

import jakarta.persistence.*;

@Entity
@Table(name = "login")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String passwordHash;

    @Column(nullable = false)
    private String salt;

    @Column(nullable = false)
    private String role;

    @Transient
    private String password; // untuk input dari form, tidak disimpan ke DB

    public User() {
        // constructor kosong, jangan generate salt di sini
    }

    // Getter dan Setter

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPasswordHash() { return passwordHash; }
    public void setPasswordHash(String passwordHash) { this.passwordHash = passwordHash; }

    public String getSalt() { return salt; }
    public void setSalt(String salt) { this.salt = salt; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    // Validasi password dengan salt dan hash yang sudah tersimpan
    public boolean checkPassword(String rawPassword) {
        return com.gudangdamar.main.util.PasswordUtils.verifyPassword(rawPassword, this.salt, this.passwordHash);
    }
}
