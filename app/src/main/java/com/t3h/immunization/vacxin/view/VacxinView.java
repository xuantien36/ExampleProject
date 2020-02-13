package com.t3h.immunization.vacxin.view;

import com.t3h.immunization.basemvp.MvpView;
import com.t3h.immunization.vacxin.model.InjectionGroup;

import java.util.List;

public interface VacxinView extends MvpView {
    void onshowList( List<InjectionGroup> injectionGroup);
}
