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
    private Integer injected;
    @SerializedName("prepare_inject")
    @Expose
    private Integer prepareInject;
    @SerializedName("miss_injected")
    @Expose
    private Integer missInjected;

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

    public Integer getInjected() {
        return injected;
    }

    public void setInjected(Integer injected) {
        this.injected = injected;
    }

    public Integer getPrepareInject() {
        return prepareInject;
    }

    public void setPrepareInject(Integer prepareInject) {
        this.prepareInject = prepareInject;
    }

    public Integer getMissInjected() {
        return missInjected;
    }

    public void setMissInjected(Integer missInjected) {
        this.missInjected = missInjected;
    }

}

