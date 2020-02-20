package com.t3h.immunization.statiscal.view;
import com.t3h.immunization.basemvp.MvpView;
import com.t3h.immunization.statiscal.model.Injections;
import com.t3h.immunization.vacxin.model.InjectionGroup;

import java.util.List;

public interface StatiscalView extends MvpView {
    void showListStatiscal(List<InjectionGroup> injectionGroup,List<Injections> data);
}
