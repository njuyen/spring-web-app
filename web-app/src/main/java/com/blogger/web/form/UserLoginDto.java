package com.blogger.web.form;

import org.hibernate.validator.constraints.NotBlank;

public class UserLoginDto {
    @NotBlank
    private String username;
    
    @NotBlank
    private String password;
    
    public String getUsername() {
        return username;
    }
    public void setUsername(String name) {
        this.username = name;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}
