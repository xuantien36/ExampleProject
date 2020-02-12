package com.t3h.immunization.activity;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.muddzdev.styleabletoast.StyleableToast;
import com.t3h.immunization.R;
import com.t3h.immunization.api.ApiBuilder;
import com.t3h.immunization.respone.ResponeRegister;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SetupActivity extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.back_setup)
    ImageView imSetup;
    @BindView(R.id.btn_setup)
    Button btnSetup;
    @BindView(R.id.edt_password)
    EditText edtPass;
    @BindView(R.id.confirm_password)
    EditText edtConfirm;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        imSetup.setOnClickListener(this);
        btnSetup.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_setup:
                finish();
                break;
            case R.id.btn_setup:
                progressDialog = new ProgressDialog(this);
                progressDialog.setMessage(getResources().getString(R.string.message));
                progressDialog.setCanceledOnTouchOutside(false);
                progressDialog.show();
                btnSetup.setBackgroundColor(getResources().getColor(R.color.colorBG1));
                String confirm = edtConfirm.getText().toString();
                String pass = edtPass.getText().toString();
                confirm.equals(pass);
                    ApiBuilder.getInstance().changepass(pass).enqueue(new Callback<ResponeRegister>() {
                        @Override
                        public void onResponse(Call<ResponeRegister> call, Response<ResponeRegister> response) {
                            if (response.body().getStatus() == true) {
                                progressDialog.dismiss();
                                Intent intent = new Intent(SetupActivity.this, LoginActivity.class);
                                startActivity(intent);
                            }else {
                                progressDialog.dismiss();
                                StyleableToast.makeText(SetupActivity.this, "Error",R.style.ColoredText).show();
                            }
                        }
                        @Override
                        public void onFailure(Call<ResponeRegister> call, Throwable t) {

                        }
                    });
                    break;
                }

        }
    }

