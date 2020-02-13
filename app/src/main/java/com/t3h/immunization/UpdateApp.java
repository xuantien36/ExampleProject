package com.t3h.immunization;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UpdateApp {
    @SerializedName("platform")
    @Expose
    private String platform;
    @SerializedName("link")
    @Expose
    private String link;
    @SerializedName("version")
    @Expose
    private Integer version;
    @SerializedName("isUpdate")
    @Expose
    private Integer isUpdate;

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Integer getIsUpdate() {
        return isUpdate;
    }

    public void setIsUpdate(Integer isUpdate) {
        this.isUpdate = isUpdate;
    }

    @Override
    public String toString() {
        return "UpdateApp{" +
                "platform='" + platform + '\'' +
                ", link='" + link + '\'' +
                ", version=" + version +
                ", isUpdate=" + isUpdate +
                '}';
    }
}

