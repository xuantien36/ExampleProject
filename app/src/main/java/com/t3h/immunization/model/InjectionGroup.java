package com.t3h.immunization.model;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class InjectionGroup implements Serializable {
    @SerializedName("group_title")
    @Expose
    private String groupTitle;
    @SerializedName("group_injection")
    @Expose
    private String groupInjection;

    @SerializedName("link")
    @Expose
    private String linkUrl;

    public String getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }

    public String getGroupTitle() {
        return groupTitle;
    }

    public void setGroupTitle(String groupTitle) {
        this.groupTitle = groupTitle;
    }

    public String getGroupInjection() {
        return groupInjection;
    }

    public void setGroupInjection(String groupInjection) {
        this.groupInjection = groupInjection;
    }

}
