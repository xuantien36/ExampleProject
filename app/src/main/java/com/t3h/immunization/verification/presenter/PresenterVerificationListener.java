package com.t3h.immunization.verification.presenter;

import com.t3h.immunization.basemvp.MvpPresenter;
import com.t3h.immunization.verification.view.VerificationView;

public interface PresenterVerificationListener<V extends VerificationView>extends MvpPresenter<V> {
    void handleVerification(String code);
}
