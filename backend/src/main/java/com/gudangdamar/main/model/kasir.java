package com.gudangdamar.main.model;


public class kasir extends Role {
    private String username;
    private String password;

    public kasir(String username, String password, String Role) {
        super(Role);
        this.username = username;
        this.password = password;
    }
    public void setUsername(String username){
        User admin = new User(username, password, "admin");
        if(admin.login(username, username)){
            this.username = username;
            System.out.println("Login berhasil! Selamat datang, " + username +" !");
        }else{
            System.out.println("Login gagal! Silakan coba lagi.");
        }
    }
    public String getUsername(){
        return username;
    }
    public String getpassword(){
        return password;
    }
    public void setpassword(String password){
        this.password=password;
    }
    @Override
    public void hakAkses(User user) {//ini kaya gimana ya biar dia bisa akses dari fitur beberapa saja?
        
    }
}
