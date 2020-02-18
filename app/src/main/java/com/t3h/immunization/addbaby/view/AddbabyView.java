package com.t3h.immunization.addbaby.view;

import com.t3h.immunization.basemvp.MvpView;

public interface AddbabyView extends MvpView {
    void addSuccess();
    void addFail();
    void showToast();
}
