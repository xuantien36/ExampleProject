package com.t3h.immunization.respone;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.t3h.immunization.model.GetBaby;

import java.util.List;

public class BaByRespone {

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("data")
    @Expose
    private List<GetBaby> data = null;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public List<GetBaby> getData() {
        return data;
    }

    public void setData(List<GetBaby> data) {
        this.data = data;
    }

}

