package com.t3h.immunization.editinjection.view;
import com.t3h.immunization.basemvp.MvpView;

public interface EditInjectionView extends MvpView {
    void editSuccess();
    void onFail();
    void showToast();
}
