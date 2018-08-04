package com.example.cse.serviceengineer;


import com.google.gson.annotations.SerializedName;

public class LoginDataBringer  {
    @SerializedName("username")
    private String username;

    @SerializedName("password")
    private String password;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public LoginDataBringer(String username, String password) {

        this.username = username;
        this.password = password;
    }
}

