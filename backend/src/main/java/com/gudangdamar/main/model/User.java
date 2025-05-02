package com.gudangdamar.main.model;

// import jakarta.persistence.*;

// @Entity
public class User {

    // // Kolom id sebagai primary key
    // @Id
    // @GeneratedValue(strategy = GenerationType.IDENTITY)
    // private Long id;

    // // Kolom name
    // private String name;
    // // Kolom email, harus ada sesuai dengan yang dibutuhkan
    // private String email;
    // // Getter dan Setter untuk id
    // public Long getId() {
    //     return id;
    // }
    // public void setId(Long id) {
    //     this.id = id;
    // }

    // // Getter dan Setter untuk name
    // public String getName() {
    //     return name;
    // }

    // public void setName(String name) {
    //     this.name = name;
    // }

    // // Getter dan Setter untuk email
    // public String getEmail() {
    //     return email;
    // }

    // public void setEmail(String email) {
    //     this.email = email;
    // }
    private String username;
    private String password;
    private String role;

    public User(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        if(username == null || username.isEmpty()){
            System.out.println("Username tidak boleh kosong");
        }else{
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
    public boolean login(String username, String password) {
        return this.username.equals(username) && this.password.equals(password);
    }
}
