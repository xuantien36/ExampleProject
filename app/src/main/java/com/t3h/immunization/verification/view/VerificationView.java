package com.t3h.immunization.verification.view;

import com.t3h.immunization.basemvp.MvpView;

public interface VerificationView extends MvpView {
    void onVerificationSuccess();
    void onVerificationFail();

}
