package com.t3h.immunization.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class GetBaby implements Serializable {

    @SerializedName("baby_id")
    @Expose
    private Integer babyId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("gender")
    @Expose
    private String gender;
    @SerializedName("birthday")
    @Expose
    private String birthday;
    @SerializedName("link_avatar")
    @Expose
    private String linkAvatar;
    @SerializedName("note")
    @Expose
    private String note;
    @SerializedName("isSavedOnServer")
    @Expose
    private String isSavedOnServer;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("injected")
    @Expose
    private String injected;
    @SerializedName("not_injected")
    @Expose
    private String notInjected;
    @SerializedName("miss_injected")
    @Expose
    private String missInjected;

    public static GetBaby instant;
    public static GetBaby getInstance(){
        if (instant == null){
            instant = new GetBaby();
            return instant;
        }else {
            return  instant;
        }
    }

    public Integer getBabyId() {
        return babyId;
    }

    public void setBabyId(Integer babyId) {
        this.babyId = babyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getLinkAvatar() {
        return linkAvatar;
    }

    public void setLinkAvatar(String linkAvatar) {
        this.linkAvatar = linkAvatar;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getIsSavedOnServer() {
        return isSavedOnServer;
    }

    public void setIsSavedOnServer(String isSavedOnServer) {
        this.isSavedOnServer = isSavedOnServer;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getInjected() {
        return injected;
    }

    public void setInjected(String injected) {
        this.injected = injected;
    }

    public String getNotInjected() {
        return notInjected;
    }

    public void setNotInjected(String notInjected) {
        this.notInjected = notInjected;
    }

    public String getMissInjected() {
        return missInjected;
    }

    public void setMissInjected(String missInjected) {
        this.missInjected = missInjected;
    }

}