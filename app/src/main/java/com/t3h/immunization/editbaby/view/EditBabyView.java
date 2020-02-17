package com.t3h.immunization.editbaby.view;

import com.t3h.immunization.basemvp.MvpView;

public interface EditBabyView extends MvpView {
    void editSuccess();
    void onFail();
}
