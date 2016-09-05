package com.supplier.web.form;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

import com.supplier.web.form.validator.Matcher;
import com.supplier.web.form.validator.UserUnique;

@Matcher(field1 = "password", field2 = "confirmPassword")
public class UserCreateDto {

    @NotBlank
    @UserUnique
    private String name;

    @NotBlank
    @Pattern(regexp = "")
    private String password;

    private String confirmPassword;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

}
