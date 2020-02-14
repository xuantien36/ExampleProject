package com.t3h.immunization.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.muddzdev.styleabletoast.StyleableToast;
import com.t3h.immunization.login.presenter.PresenterLogin;
import com.t3h.immunization.R;
import com.t3h.immunization.api.ApiBuilder;
import com.t3h.immunization.login.model.User;
import com.t3h.immunization.login.view.LoginView;
import com.t3h.immunization.respone.ResponeLogin;
import com.t3h.immunization.utils.AppPreferences;
import com.t3h.immunization.utils.SaveData;
import butterknife.BindView;
import butterknife.ButterKnife;

import static com.t3h.immunization.utils.Constant.KEY_NEXT;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, LoginView {
    @BindView(R.id.btn_register)
    Button btnRegister;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.edt_name_login)
    EditText edtName;
    @BindView(R.id.edt_password)
    EditText edtPass;
    @BindView(R.id.checkbox)
    CheckBox checkBox;
    private SharedPreferences sharedPreferences;
    @BindView(R.id.tv_forget)
    TextView tvForget;
    private Handler handler =new Handler();
    private ProgressDialog progressDialog;
    private PresenterLogin presenterLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SaveData.updateLangua(this);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        intView();
    }

    private void intView() {
        AppPreferences.getInstance(getApplicationContext()).putBoolean(KEY_NEXT, true);
        btnLogin.setOnClickListener(this);
        btnRegister.setOnClickListener(this);
        tvForget.setOnClickListener(this);
       edtName.setText( AppPreferences.getInstance(getApplicationContext()).getString(("taikhoan")));
      edtPass.setText( AppPreferences.getInstance(getApplicationContext()).getString("matkhau"));
        Log.e("LOGIN", "key Check box  "+AppPreferences.getInstance(getApplicationContext()).getBoolean("checked") );
        checkBox.setChecked(AppPreferences.getInstance(getApplicationContext()).getBoolean("checked"));
        presenterLogin =new PresenterLogin(this);
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_register:
                Intent register = new Intent(this, RegisterActivity.class);
                startActivity(register);
                break;
            case R.id.tv_forget:
                Intent intent = new Intent(this, ForgotActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_login:
                String user_name = edtName.getText().toString();
                String password = edtPass.getText().toString();
                presenterLogin.receivedHandlrLogin(user_name, password);
                if (checkBox.isChecked()) {
                    AppPreferences.getInstance(getApplicationContext()).putString("taikhoan", user_name);
                    AppPreferences.getInstance(getApplicationContext()).putString("matkhau", password);
                    AppPreferences.getInstance(getApplicationContext()).putBoolean("checked", true);
                    Log.e("LOGIN", "onResponse: check  " +
                            AppPreferences.getInstance(getApplicationContext()).getBoolean("checked"));

                } else {
                    AppPreferences.getInstance(getApplicationContext()).putString("taikhoan", "");
                    AppPreferences.getInstance(getApplicationContext()).putString("matkhau", "");
                    AppPreferences.getInstance(getApplicationContext()).putBoolean("checked", false);
                    Log.e("LOGIN", "onResponse: not check " +
                            AppPreferences.getInstance(getApplicationContext()).getBoolean("checked"));
                    break;
                }
        }
    }
    @Override
    public void onLoginSuccess() {
        Intent intent = new Intent(LoginActivity.this, CategoriActivity.class);
        startActivity(intent);
        StyleableToast.makeText(this,"Error",R.style.ColoredText).show();

    }
    @Override
    public void onLoginFail() {
        StyleableToast.makeText(this,"Error",R.style.ColoredText).show();

    }
}



