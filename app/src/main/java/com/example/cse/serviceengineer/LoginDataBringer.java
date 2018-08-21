package com.example.cse.serviceengineer;


import com.google.gson.annotations.SerializedName;

public class LoginDataBringer  {
    @SerializedName("phone")
    private String phone;

    @SerializedName("otp")
    private String otp;

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public String getOtp() {
        return otp;
    }

    public LoginDataBringer(String phone, String otp, String username, String password) {

        this.phone = phone;
        this.otp = otp;
        this.username = username;
        this.password = password;
    }

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

