package com.t3h.immunization.view.bookinjection;

import com.t3h.immunization.basemvp.MvpView;
import com.t3h.immunization.model.InjectionGroup;
import com.t3h.immunization.model.Injections;

import java.util.List;

public interface InjectionBookView extends MvpView {
    void  onshowList(List<InjectionGroup> groups, List<Injections> data);
}
