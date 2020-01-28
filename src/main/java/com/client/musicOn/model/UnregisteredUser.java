package com.client.musicOn.model;

import com.client.musicOn.validation.FieldMatch;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@FieldMatch.List(
        @FieldMatch(first = "password", second = "matchingPassword", message = "Passwords must match")
)
public class UnregisteredUser {

    @NotNull
    @Size(min = 3,max = 15,message = "Username is required")
    private String userName;

    @NotNull
    @Size(min = 3,max = 15,message = "Password is required")
    private String password;

    @NotNull
    @Size(min = 3,max = 15,message = "Password is required")
    private String matchingPassword;

    @NotNull
    @Size(min = 3,max = 30,message = "Email is required")
    @Email
    private String email;

    public UnregisteredUser() {
    }

    public UnregisteredUser(@NotNull @Size(min = 3, max = 15, message = "Username is required") String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMatchingPassword() {
        return matchingPassword;
    }

    public void setMatchingPassword(String matchingPassword) {
        this.matchingPassword = matchingPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
