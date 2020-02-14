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
import com.t3h.immunization.register.presenter.PresenterRegister;
import com.t3h.immunization.register.view.RegisterView;
import com.t3h.immunization.respone.ResponeRegister;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener, RegisterView {
    @BindView(R.id.im_back)
    ImageView imBack;
    @BindView(R.id.btn_register)
    Button btnRegister;
    @BindView(R.id.edt_user)
    EditText edtUser;
    @BindView(R.id.edt_name_register)
    EditText edtName;
    @BindView(R.id.edt_email)
    EditText edtEmail;
    @BindView(R.id.edt_pass)
    EditText edtPass;
    @BindView(R.id.edt_enter_password)
    EditText edtEnterPass;
    @BindView(R.id.edt_phone)
    EditText edtPhone;
        private ProgressDialog progressDialog;
    private PresenterRegister register;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(getResources().getString(R.string.message));
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();
        register = new PresenterRegister(this);
        imBack.setOnClickListener(this);
        btnRegister.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_register:
                String user_name = edtUser.getText().toString();
                String name = edtName.getText().toString();
                String password = edtPass.getText().toString();
                String phone = edtPhone.getText().toString();
                String email = edtEmail.getText().toString();

                register.receiedHandleRegister(user_name, name, password, phone, email);

//                if (user_name.equals("")||password.equals("")||email.equals("")|| name.equals("")){
//                    Toast.makeText(this,getResources().getString(R.string.toast), Toast.LENGTH_SHORT).show();
//
//                }
//                progressDialog = new ProgressDialog(this);
//                progressDialog.setMessage(getResources().getString(R.string.message));
//                progressDialog.setCanceledOnTouchOutside(false);
//                progressDialog.show();
//                ApiBuilder.getInstance().register(user_name, password,"",name, phone, email,"","")
//                        .enqueue(new Callback<ResponeRegister>() {
//                    @Override
//                    public void onResponse(Call<ResponeRegister> call, Response<ResponeRegister> response) {
//                        if (response.body().getStatus() == true) {
//                            progressDialog.dismiss();
//                            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
//                            startActivity(intent);
//                            finish();
//                            Toast.makeText(RegisterActivity.this, "Register success", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                    @Override
//                    public void onFailure(Call<ResponeRegister> call, Throwable t) {
//                        progressDialog.dismiss();
//                        Toast.makeText(RegisterActivity.this, "Error", Toast.LENGTH_SHORT).show();
//
//                    }
//                });
                if (user_name.equals("")||password.equals("")||email.equals("")|| name.equals("")){
                    StyleableToast.makeText(RegisterActivity.this, getResources().getString(R.string.toast),R.style.ColoredText).show();

                }
                progressDialog = new ProgressDialog(this,R.style.CustomDialog);
                progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                progressDialog.setMessage(getResources().getString(R.string.message));
                progressDialog.setCanceledOnTouchOutside(false);
                progressDialog.show();
                ApiBuilder.getInstance().register(user_name, password,"",name, phone, email,"","").enqueue(new Callback<ResponeRegister>() {
                    @Override
                    public void onResponse(Call<ResponeRegister> call, Response<ResponeRegister> response) {
                        if (response.body().getStatus() == true) {
                            progressDialog.dismiss();
                            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                            startActivity(intent);
                            finish();
                            StyleableToast.makeText(RegisterActivity.this, "Register success",R.style.ColoredText).show();
                        }
                    }
                    @Override
                    public void onFailure(Call<ResponeRegister> call, Throwable t) {
                        progressDialog.dismiss();
                        StyleableToast.makeText(RegisterActivity.this, "Error",R.style.ColoredText).show();

                    }
                });
                break;
            case R.id.im_back:
                finish();
                break;
        }

    }

    @Override
    public void onRegisterSuccess() {
        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
        startActivity(intent);
        Toast.makeText(RegisterActivity.this, "Register success", Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void onRegisterFail() {
        Toast.makeText(RegisterActivity.this, "Error", Toast.LENGTH_SHORT).show();

    }
}
