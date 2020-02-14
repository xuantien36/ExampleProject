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

public class VerificationActivity extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.back_verification)
    ImageView imVerification;
    @BindView(R.id.btn_verification)
    Button btnVerification;
    @BindView(R.id.edt_verification)
    EditText edtVerification;
    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification);
        ButterKnife.bind(this);
        init();
    }
    private void init() {
        imVerification.setOnClickListener(this);
        btnVerification.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.back_verification:
                finish();
                break;
            case R.id.btn_verification:
                progressDialog = new ProgressDialog(this);
                progressDialog.setMessage(getResources().getString(R.string.message));
                progressDialog.setCanceledOnTouchOutside(false);
                progressDialog.show();
                btnVerification.setBackgroundColor(getResources().getColor(R.color.colorBG1));
                String code =edtVerification.getText().toString();
                ApiBuilder.getInstance().verifycode(code).enqueue(new Callback<ResponeRegister>() {
                    @Override
                    public void onResponse(Call<ResponeRegister> call, Response<ResponeRegister> response) {
                        if (response.body().getStatus() == true){
                            progressDialog.dismiss();
                            Intent intent=new Intent(VerificationActivity.this,SetupActivity.class);
                            startActivity(intent);
                        }else {
                            progressDialog.dismiss();
                            StyleableToast.makeText(VerificationActivity.this,"Error",R.style.ColoredText).show();
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
