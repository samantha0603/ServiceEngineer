package com.example.cse.serviceengineer;

import com.google.gson.annotations.SerializedName;

public class Report_Data_Bringer {
    @SerializedName("EngineerName")
    private String engineername;
    @SerializedName("AssignedWork")
    private String assignedwork;
    @SerializedName("completed")
    private String completed;
    @SerializedName("TotalRevenue")
    private String totalrevenue;
    @SerializedName("Status")
    private String status;

    public String getEngineername() {
        return engineername;
    }

    public String getAssignedwork() {
        return assignedwork;
    }

    public String getCompleted() {
        return completed;
    }

    public String getTotalrevenue() {
        return totalrevenue;
    }

    public String getStatus() {
        return status;
    }

    public Report_Data_Bringer(String engineername, String assignedwork, String completed, String totalrevenue, String status) {

        this.engineername = engineername;
        this.assignedwork = assignedwork;
        this.completed = completed;
        this.totalrevenue = totalrevenue;
        this.status = status;
    }
}
