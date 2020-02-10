package com.t3h.immunization.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.t3h.immunization.R;
import com.t3h.immunization.api.ApiBuilder;
import com.t3h.immunization.model.UpdateApp;
import com.t3h.immunization.model.User;
import com.t3h.immunization.respone.ResponeApp;
import com.t3h.immunization.respone.ResponeLogin;
import com.t3h.immunization.utils.AppPreferences;
import com.t3h.immunization.utils.Constant;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashActivity extends AppCompatActivity implements View.OnClickListener {
    private Handler handler;
    private Dialog dialog;
    Button btnNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        initView();
    }

    public void showDialog(String i) {
        dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_update);
        dialog.setCanceledOnTouchOutside(false);
        Button btnYes = dialog.findViewById(R.id.btn_yes);
        btnNo = dialog.findViewById(R.id.btn_no);
        btnYes.setOnClickListener(this);
        btnNo.setOnClickListener(this);
        if (Integer.parseInt(i) == 2) {
            btnNo.setVisibility(View.GONE);
        }
        dialog.show();
    }

    public void saveData() {
        handler = new Handler();
        handler.postDelayed(() -> {
            if (AppPreferences.getInstance(getApplicationContext()).getBoolean(Constant.KEY_NEXT)) {
                if (AppPreferences.getInstance(getApplicationContext()).getBoolean("checked")) {
                    handlerLogin();
                } else {
                    startActivity(new Intent(SplashActivity.this, LoginActivity.class));

                }
            } else {
                startActivity(new Intent(SplashActivity.this, WelcomeActivity.class));
            }
        }, 1500);
    }
    private void initView() {
        ApiBuilder.getInstance().updateApp("android", 1).enqueue(new Callback<ResponeApp>() {
            @Override
            public void onResponse(Call<ResponeApp> call, Response<ResponeApp> response) {
                UpdateApp data = response.body().getData();
                if (data.getIsUpdate() == 0) {
                    saveData();
                } else if (data.getIsUpdate() == 1) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            showDialog(String.valueOf(data.getIsUpdate()));
                        }
                    });
                } else if (data.getIsUpdate() == 2) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            showDialog(String.valueOf(data.getIsUpdate()));
                        }
                    });

                }
            }

            @Override
            public void onFailure(Call<ResponeApp> call, Throwable t) {

            }
        });
    }

    public void handlerLogin() {
        ApiBuilder.getInstance().login(AppPreferences.getInstance(getApplicationContext()).getString("taikhoan"),
                AppPreferences.getInstance(getApplicationContext()).getString("matkhau")).enqueue(new Callback<ResponeLogin>() {
            @Override
            public void onResponse(Call<ResponeLogin> call, Response<ResponeLogin> response) {
                if (response.body().getStatus() == true) {
                    User.getInstans().setId(response.body().getData().getId());
                    Intent intent = new Intent(SplashActivity.this, CategoriActivity.class);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
                finish();
            }
            @Override
            public void onFailure(Call<ResponeLogin> call, Throwable t) {
                Toast.makeText(SplashActivity.this, "Error", Toast.LENGTH_SHORT).show();
                Log.e("TAG", "CHECK LOGIN failed!");
                t.printStackTrace();
            }
        });
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_yes:
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=vnshine.com.sotiemchung")));
                break;
            case R.id.btn_no:
                saveData();
                break;
        }

    }
}
