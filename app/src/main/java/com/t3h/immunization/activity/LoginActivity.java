package com.t3h.immunization.activity;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.t3h.immunization.R;
import com.t3h.immunization.api.ApiBuilder;
import com.t3h.immunization.model.ResponeLogin;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        intView();
    }

    private void intView() {
        btnLogin.setOnClickListener(this);
        btnRegister.setOnClickListener(this);
        tvForget.setOnClickListener(this);
        sharedPreferences = getSharedPreferences("SaveDataLogin", Context.MODE_PRIVATE);
        edtName.setText(sharedPreferences.getString("taikhoan", ""));
        edtPass.setText(sharedPreferences.getString("matkhau", ""));
        checkBox.setChecked(sharedPreferences.getBoolean("checked", false));
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_register:
                Intent register = new Intent(this, RegisterActivity.class);
                startActivity(register);
                break;
            case R.id.tv_forget:
                Intent intent = new Intent(this,ForgotActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_login:
                String user_name = edtName.getText().toString();
                String password = edtPass.getText().toString();
                if (user_name.isEmpty() && password.isEmpty()) {
                    Toast.makeText(this, "Please complete the form", Toast.LENGTH_SHORT).show();
                } else {
                    ApiBuilder.getInstance().login(user_name, password).enqueue(new Callback<ResponeLogin>() {
                        @Override
                        public void onResponse(Call<ResponeLogin> call, Response<ResponeLogin> response) {
                            if (response.body().getStatus() == true) {
                                Log.e("TAG","CHECK LOGIN SUCCESS!");
                                Intent intent = new Intent(LoginActivity.this, CategoriActivity.class);
                                startActivity(intent);
                                Toast.makeText(LoginActivity.this, "Login Success", Toast.LENGTH_SHORT).show();
                                if (checkBox.isChecked()) {
                                    SharedPreferences.Editor editor = sharedPreferences.edit();
                                    editor.putString("taikhoan", user_name);
                                    editor.putString("matkhau", password);
                                    editor.putBoolean("checked", true);
                                    editor.commit();
                                } else {
                                    SharedPreferences.Editor editor = sharedPreferences.edit();
                                    editor.remove("taikhoan");
                                    editor.remove("matkhau");
                                    editor.remove("checked");
                                    editor.commit();

                                }
                            }else {
                                Toast.makeText(LoginActivity.this, "Error", Toast.LENGTH_SHORT).show();
                            }

                        }
                        @Override
                        public void onFailure(Call<ResponeLogin> call, Throwable t) {
                            Toast.makeText(LoginActivity.this, "Error", Toast.LENGTH_SHORT).show();
                            Log.e("TAG","CHECK LOGIN failed!");
                            t.printStackTrace();
                        }
                    });

                }
        }
    }

}

