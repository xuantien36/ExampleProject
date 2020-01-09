package com.t3h.immunization.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Injections {
    @SerializedName("baby_id")
    @Expose
    private String babyId;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("note")
    @Expose
    private String note;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("medicine")
    @Expose
    private String medicine;
    @SerializedName("group_injection")
    @Expose
    private String groupInjection;
    @SerializedName("isInjected")
    @Expose
    private String isInjected;
    @SerializedName("injected_date")
    @Expose
    private String injectedDate;

    public String getBabyId() {
        return babyId;
    }

    public void setBabyId(String babyId) {
        this.babyId = babyId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMedicine() {
        return medicine;
    }

    public void setMedicine(String medicine) {
        this.medicine = medicine;
    }

    public String getGroupInjection() {
        return groupInjection;
    }

    public void setGroupInjection(String groupInjection) {
        this.groupInjection = groupInjection;
    }

    public String getIsInjected() {
        return isInjected;
    }

    public void setIsInjected(String isInjected) {
        this.isInjected = isInjected;
    }

    public String getInjectedDate() {
        return injectedDate;
    }

    public void setInjectedDate(String injectedDate) {
        this.injectedDate = injectedDate;
    }

}


