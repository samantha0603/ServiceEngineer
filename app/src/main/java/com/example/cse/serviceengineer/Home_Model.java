package com.example.cse.serviceengineer;

import com.google.gson.annotations.SerializedName;

public class Home_Model {
    @SerializedName("productname")
    private String  productname;
    @SerializedName("issue")
    private String issue;
    @SerializedName("description")
    private String description;
    @SerializedName("customername")
    private String customername;
    @SerializedName("phno")
    private String phno;
    @SerializedName("address")
    private String address;
    @SerializedName("status")
    private String status;


    public String getIssue() {
        return issue;
    }

    public String getDescription() {
        return description;
    }

    public String getCustomername() {
        return customername;
    }

    public String getPhno() {
        return phno;
    }

    public String getAddress() {
        return address;
    }

    public String getStatus() {
        return status;
    }

    public String getProductname() {

        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public void setIssue(String issue) {
        this.issue = issue;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCustomername(String customername) {
        this.customername = customername;
    }

    public void setPhno(String phno) {
        this.phno = phno;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
