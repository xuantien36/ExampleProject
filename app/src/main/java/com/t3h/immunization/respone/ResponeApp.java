package com.t3h.immunization.respone;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.t3h.immunization.model.User;

public class ResponeApp {
    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("data")
    @Expose
    private User data;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public User getData() {
        return data;
    }

    public void setData(User data) {
        this.data = data;
    }

}

