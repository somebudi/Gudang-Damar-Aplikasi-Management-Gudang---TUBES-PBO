package com.gudangdamar.main.model;

public class admin extends Role {
    private String username;
    private String password;

    public admin(String username, String password, String Role) {
        super(Role);
        this.username = username;
        this.password = password;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getpassword() {
        return password;
    }
    public void setpassword(String password) {
        this.password = password;
    }

    @Override
    public void hakAkses(User user) {
        
    }
    
}
