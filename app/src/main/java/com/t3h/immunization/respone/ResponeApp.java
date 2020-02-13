package com.t3h.immunization.respone;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.t3h.immunization.UpdateApp;

public class ResponeApp {

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("data")
    @Expose
    private UpdateApp data;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public UpdateApp getData() {
        return data;
    }

    public void setData(UpdateApp data) {
        this.data = data;
    }

}
