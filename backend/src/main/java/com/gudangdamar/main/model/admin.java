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
    public void setusername(String username){//apakah sudah benar?
        User admin = new User(username, password, "admin");
        if(admin.login(username, username)){
            this.username = username;
            System.out.println("Login berhasil! Selamat datang, " + username+" !");
        }else{
            System.out.println("Login gagal! Silakan coba lagi.");
        }
    }
    public String getpassword() {
        return password;
    }
    public void setpassword(String password) {
        this.password = password;
    }

    @Override
    public void hakAkses(User user) {//ini kaya gimana ya biar dia bisa akses dari fitur beberapa saja?
        
    }
    
}
