package com.example.demo.dto;

public class UserRegisterDTO {

    private String fullName;
    private String email;
    private String password;
    private String comfrimPassword;

    public UserRegisterDTO() {
    }

    public UserRegisterDTO(String email, String password, String fullName) {
        this.fullName = fullName;
        this.email = email;
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getComfrimPassword() {
        return comfrimPassword;
    }

    public void setComfrimPassword(String comfrimPassword) {
        this.comfrimPassword = comfrimPassword;
    }
}
