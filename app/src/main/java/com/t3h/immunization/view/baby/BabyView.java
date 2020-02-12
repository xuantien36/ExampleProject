package com.t3h.immunization.view.baby;

import com.t3h.immunization.basemvp.MvpView;
import com.t3h.immunization.model.GetBaby;

import java.util.List;

public interface BabyView extends MvpView {
    void showList(List<GetBaby> data);
}
