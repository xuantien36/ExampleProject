package com.t3h.immunization.activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import com.muddzdev.styleabletoast.StyleableToast;
import com.t3h.immunization.R;
import com.t3h.immunization.basemvp.BaseActivity;
import com.t3h.immunization.forgotpass.presenter.PresenterForgot;
import com.t3h.immunization.forgotpass.view.ForgotView;
import butterknife.BindView;
import butterknife.ButterKnife;


public class ForgotActivity extends BaseActivity<PresenterForgot> implements View.OnClickListener, ForgotView {
    @BindView(R.id.back_forgot)
    ImageView imBack;
    @BindView(R.id.btn_forgot)
    Button btnForgot;
    @BindView(R.id.edt_email)
    EditText edtEmail;
    private ProgressDialog progressDialog;
    private Handler handler=new Handler();

    @Override
    protected PresenterForgot loadPresenter() {
        return new PresenterForgot();
    }
    @Override
    protected void initData() {

    }
    @Override
    protected void initListener() {

    }
    @Override
    protected int getLayoutId() {
        return R.layout.activity_forgot;
    }
    @Override
    protected void initView() {
        ButterKnife.bind(this);
        mPresenter = new PresenterForgot();
        mPresenter.onAttach(this);
        imBack.setOnClickListener(this);
        btnForgot.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_forgot:
                finish();
                break;
            case R.id.btn_forgot:
                progressDialog = new ProgressDialog(this,R.style.CustomDialog);
                progressDialog.setMessage(getResources().getString(R.string.message));
                progressDialog.setCanceledOnTouchOutside(false);
                progressDialog.show();
                btnForgot.setBackgroundColor(getResources().getColor(R.color.colorBG1));
                String email = edtEmail.getText().toString();
                mPresenter.onHandleForgot(email);
                break;
        }

    }
    @Override
    public void onSuccess() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                progressDialog.dismiss();

            }
        },500);
        Intent intent = new Intent(ForgotActivity.this, VerificationActivity.class);
        startActivity(intent);

    }
    @Override
    public void onFail() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                progressDialog.dismiss();

            }
        },500);
        StyleableToast.makeText(this,getResources().getString(R.string.error),R.style.ColoredText).show();

    }
}
