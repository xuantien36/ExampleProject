package com.t3h.immunization.respone;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.t3h.immunization.model.InjectionGroup;
import com.t3h.immunization.model.Injections;

import java.util.List;

public class ResponeStatistical {

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("data")
    @Expose
    private List<Injections> data = null;
    @SerializedName("injection_group")
    @Expose
    private List<InjectionGroup> injectionGroup = null;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public List<Injections> getData() {
        return data;
    }

    public void setData(List<Injections> data) {
        this.data = data;
    }

    public List<InjectionGroup> getInjectionGroup() {
        return injectionGroup;
    }

    public void setInjectionGroup(List<InjectionGroup> injectionGroup) {
        this.injectionGroup = injectionGroup;
    }

}