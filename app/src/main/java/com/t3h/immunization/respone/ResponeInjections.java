package com.t3h.immunization.respone;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.t3h.immunization.vacxin.model.InjectionGroup;

import java.util.List;

public class ResponeInjections {
    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("data")
    @Expose
    private List<InjectionGroup> injectionGroup = null;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public List<InjectionGroup> getInjectionGroup() {
        return injectionGroup;
    }

    public void setInjectionGroup(List<InjectionGroup> injectionGroup) {
        this.injectionGroup = injectionGroup;
    }

}