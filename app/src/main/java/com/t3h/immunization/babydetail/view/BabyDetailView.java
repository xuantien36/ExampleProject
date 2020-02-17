package com.t3h.immunization.babydetail.view;

import com.t3h.immunization.basemvp.MvpView;

public interface BabyDetailView extends MvpView {
    void deleteSuccess();
    void onFail();
}
