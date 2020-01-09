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

public class ForgotActivity extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.back_forgot)
    ImageView imBack;
    @BindView(R.id.btn_forgot)
    Button btnForgot;
    @BindView(R.id.edt_email)
    EditText edtEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        imBack.setOnClickListener(this);
        btnForgot.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.back_forgot:
                finish();
                break;
            case R.id.btn_forgot:
                btnForgot.setBackgroundColor(getResources().getColor(R.color.colorBG1));

                String email=edtEmail.getText().toString();
                ApiBuilder.getInstance().forgot(email).enqueue(new Callback<ResponeRegister>() {
                    @Override
                    public void onResponse(Call<ResponeRegister> call, Response<ResponeRegister> response) {
                        if (response.body().getStatus()==true){
                            Intent intent=new Intent(ForgotActivity.this,VerificationActivity.class);
                            startActivity(intent);
                        }else {
                            Toast.makeText(ForgotActivity.this, "Error", Toast.LENGTH_SHORT).show();
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
