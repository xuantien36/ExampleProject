package com.t3h.immunization.view.vacxin;

import com.t3h.immunization.basemvp.MvpView;
import com.t3h.immunization.model.InjectionGroup;

import java.util.List;

public interface VacxinView extends MvpView {
    void onshowList( List<InjectionGroup> injectionGroup);
}
