package com.t3h.immunization.login.presenter;

import com.t3h.immunization.basemvp.MvpPresenter;
import com.t3h.immunization.login.view.LoginView;

public interface PresenterLoginListener<V extends LoginView>extends MvpPresenter<V> {
    void hadleLogin(String userName,String pssWord);

}
