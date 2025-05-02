package com.gudangdamar.main.model;

public class kasir extends Role {
    private String username;
    private String password;
    private String Role;

    public kasir(String username, String password, String Role) {
        super(Role);
        this.username = username;
        this.password = password;
    }
    public String getusername(){
        return username;
    }
    public void setusername(String username){
        if(username == null || username.isEmpty()){
            System.out.println("Username tidak boleh kosong");
        }else{
            this.username = username;
        }
    }
    public String getpassword(){
        return password;
    }
    public void setpassword(String password){
        this.password=password;
    }
    public String getRole(){
        return Role;
    }
    public void setRole(String Role){
        this.Role=Role;
    }
    @Override
    public void hakAkses(User user) {
        
    }
}
