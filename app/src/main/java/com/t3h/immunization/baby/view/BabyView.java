package com.t3h.immunization.baby.view;

import com.t3h.immunization.basemvp.MvpView;
import com.t3h.immunization.baby.model.GetBaby;

import java.util.List;

public interface BabyView extends MvpView {
    void onshowList(List<GetBaby> data);
    void showToas();
}
