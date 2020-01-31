package com.t3h.immunization.activity;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import com.t3h.immunization.R;
import com.t3h.immunization.api.ApiBuilder;
import com.t3h.immunization.respone.ResponeRegister;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        imBack.setOnClickListener(this);
        btnRegister.setOnClickListener(this);

    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_register:
                String user_name = edtUser.getText().toString();
                String name=edtName.getText().toString();
                String password = edtPass.getText().toString();
                String phone = edtPhone.getText().toString();
                String email = edtEmail.getText().toString();

                if (user_name.equals("")||password.equals("")||email.equals("")|| name.equals("")){
                    Toast.makeText(this, "Điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();

                }
                ApiBuilder.getInstance().register(user_name, password,null,name, phone, email,null,null).enqueue(new Callback<ResponeRegister>() {
                    @Override
                    public void onResponse(Call<ResponeRegister> call, Response<ResponeRegister> response) {
                        if (response.body().getStatus() == true) {
                            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                            startActivity(intent);
                            finish();
                            Toast.makeText(RegisterActivity.this, "Register success", Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onFailure(Call<ResponeRegister> call, Throwable t) {

                    }
                });

                break;
            case R.id.im_back:
                finish();
                break;
        }

    }
}
