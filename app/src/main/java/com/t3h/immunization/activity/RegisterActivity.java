package com.t3h.immunization.activity;
import androidx.appcompat.app.AppCompatActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import com.muddzdev.styleabletoast.StyleableToast;
import com.t3h.immunization.R;
import com.t3h.immunization.basemvp.BaseActivity;
import com.t3h.immunization.register.presenter.PresenterRegister;
import com.t3h.immunization.register.view.RegisterView;
import butterknife.BindView;
import butterknife.ButterKnife;


public class RegisterActivity extends BaseActivity<PresenterRegister> implements View.OnClickListener, RegisterView {
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
    private Handler handler=new Handler();

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(getResources().getString(R.string.message));
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();
        mPresenter = new PresenterRegister();
        mPresenter.onAttach(this);
        imBack.setOnClickListener(this);
        btnRegister.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_register:
                progressDialog = new ProgressDialog(this);
                progressDialog.setMessage(getResources().getString(R.string.message));
                progressDialog.setCanceledOnTouchOutside(false);
                progressDialog.show();
                String user_name = edtUser.getText().toString();
                String name = edtName.getText().toString();
                String password = edtPass.getText().toString();
                String phone = edtPhone.getText().toString();
                String email = edtEmail.getText().toString();
               mPresenter.receiedHandleRegister(user_name,name,password,phone,email);

            case R.id.im_back:
                finish();
                break;
        }
    }
    @Override
    public void onRegisterSuccess() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                progressDialog.dismiss();
            }
        },2000);
        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
        startActivity(intent);
        Toast.makeText(RegisterActivity.this, "Register success", Toast.LENGTH_SHORT).show();
        finish();
    }
    @Override
    public void onRegisterFail() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                progressDialog.dismiss();
            }
        },2000);
        StyleableToast.makeText(RegisterActivity.this,getResources().getString(R.string.error),R.style.ColoredText).show();
    }

    @Override
    protected PresenterRegister loadPresenter() {
        return null;
    }

    @Override
    protected void initData() {

    }
    @Override
    protected void initListener() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_register;
    }
}
