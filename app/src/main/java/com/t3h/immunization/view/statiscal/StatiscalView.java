package com.t3h.immunization.view.statiscal;

import com.t3h.immunization.basemvp.MvpView;
import com.t3h.immunization.model.InjectionGroup;
import com.t3h.immunization.model.Injections;

import java.util.List;

public interface StatiscalView extends MvpView {
    void showListStatiscal(List<InjectionGroup> injectionGroup,List<Injections> data);
}
