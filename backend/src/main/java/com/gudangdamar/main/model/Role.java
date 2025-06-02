package com.gudangdamar.main.model;


public abstract class Role{
    private String Role;

    public Role(String Role) {
        this.Role = Role;
    }
    public String getRole() {
        return Role;
    }
    public void setRole(String role) {
        this.Role = role;
    }
}
