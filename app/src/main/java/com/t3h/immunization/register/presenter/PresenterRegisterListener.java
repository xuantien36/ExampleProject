package com.t3h.immunization.register.presenter;

import com.t3h.immunization.basemvp.MvpPresenter;
import com.t3h.immunization.register.view.RegisterView;

public interface PresenterRegisterListener<V extends RegisterView>extends MvpPresenter<V> {
   void receiedHandleRegister(String user_name, String name, String password, String phone, String email);
}
