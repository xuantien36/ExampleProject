package com.t3h.immunization.model;

import android.util.Log;

import com.t3h.immunization.api.ApiBuilder;
import com.t3h.immunization.presenter.login.ModelPresenterLoginListener;
import com.t3h.immunization.respone.ResponeLogin;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ModelLogin {
    private ModelPresenterLoginListener callback;

    public ModelLogin(ModelPresenterLoginListener callback) {
        this.callback = callback;
    }

    public void handleLogin(String user_name, String password){

//        String user_name = edtName.getText().toString();
//        String password = edtPass.getText().toString();
        if (user_name.isEmpty() && password.isEmpty()) {

//            Toast.makeText(this,getResources().getString(R.string.toast), Toast.LENGTH_SHORT).show();
        }
//        progressDialog = new ProgressDialog(this);
//        progressDialog.setMessage(getResources().getString(R.string.message));
//        progressDialog.setCanceledOnTouchOutside(false);
//        progressDialog.show();
        ApiBuilder.getInstance().login(user_name, password).enqueue(new Callback<ResponeLogin>() {
            @Override
            public void onResponse(Call<ResponeLogin> call, Response<ResponeLogin> response) {
                if (response.body().getStatus() == true) {
                    Log.e("TAG", "CHECK LOGIN SUCCESS!");
//                    handler.postDelayed(new Runnable() {
//                        @Override
//                        public void run() {
//                            progressDialog.dismiss();
//
//                        }
//                    },500);
                    User.getInstans().setId(response.body().getData().getId());
                    callback.onLoginSuccess();


                    }else {
                    callback.onLoginFail();
                }

            }

            @Override
            public void onFailure(Call<ResponeLogin> call, Throwable t) {
                Log.e("TAG", "CHECK LOGIN failed!");
                t.printStackTrace();
            }
        });
    }

    }
