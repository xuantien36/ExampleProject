package com.t3h.immunization.basemvp;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

public abstract class BaseActivity<V extends BasePresenter> extends AppCompatActivity {
    protected View view;
    protected V mPresenter;
    protected Activity activity;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getView());
        mPresenter = loadPresenter();
        activity = this;
        initView();
        initListener();
        initData();
    }
    protected abstract V loadPresenter();

    protected abstract void initData();

    protected abstract void initListener();

    protected abstract void initView();

    protected abstract int getLayoutId();
    public View getView() {
        view = View.inflate(this, getLayoutId(), null);
        return view;
    }
}
