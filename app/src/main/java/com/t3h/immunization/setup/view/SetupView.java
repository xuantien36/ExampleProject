package com.t3h.immunization.setup.view;

import com.t3h.immunization.basemvp.MvpView;

public interface SetupView extends MvpView {
    void onSetupSuccess();
    void onSetupFail();
}
