package com.t3h.immunization.bookinjection.view;

import com.t3h.immunization.basemvp.MvpView;
import com.t3h.immunization.vacxin.model.InjectionGroup;
import com.t3h.immunization.statiscal.model.Injections;

import java.util.List;

public interface InjectionBookView extends MvpView {
    void  onshowList(List<InjectionGroup> groups, List<Injections> data);
}
