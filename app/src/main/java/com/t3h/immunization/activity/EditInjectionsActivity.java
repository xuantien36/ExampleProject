package com.t3h.immunization.activity;
import androidx.appcompat.app.AppCompatActivity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.muddzdev.styleabletoast.StyleableToast;
import com.t3h.immunization.R;
import com.t3h.immunization.api.ApiBuilder;
import com.t3h.immunization.baby.model.GetBaby;
import com.t3h.immunization.basemvp.BaseActivity;
import com.t3h.immunization.editinjection.presenter.PresenterEditInjection;
import com.t3h.immunization.editinjection.view.EditInjectionView;
import com.t3h.immunization.login.model.User;
import com.t3h.immunization.respone.ResponeRegister;
import com.t3h.immunization.statiscal.model.Injections;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import butterknife.BindView;
import butterknife.ButterKnife;

public class EditInjectionsActivity extends BaseActivity<PresenterEditInjection> implements View.OnClickListener, EditInjectionView {
    @BindView(R.id.back_injected)
    ImageView back;
    @BindView(R.id.save_injected)
    ImageView imSave;
    @BindView(R.id.medicine)
    EditText edtMedicine;
    @BindView(R.id.note)
    EditText edtNote;
    @BindView(R.id.date)
    EditText edtDate;
    @BindView(R.id.name_injected)
    TextView nameInjected;
    @BindView(R.id.spinner)
    Spinner spinner;
    private int poss;
    private Dialog dialog;
    private Injections injections;
    private Handler handler=new Handler();

    @Override
    protected PresenterEditInjection loadPresenter() {
        return new PresenterEditInjection();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }
    @Override
    protected int getLayoutId() {
        return R.layout.activity_edit_information;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        Intent intent = getIntent();
        injections = (Injections) intent.getSerializableExtra("object");
        String date = intent.getStringExtra("child");
        String name = intent.getStringExtra("title");
        edtDate.setText(date);
        nameInjected.setText(name);
        ArrayList<String> injected = new ArrayList<>();
        injected.add("Bỏ lỡ");
        injected.add("Đã tiêm");
        injected.add("Chưa tiêm");

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, injected);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        if (injections.getIsInjected().equalsIgnoreCase("1")) {
            spinner.setSelection(1);
        } else if (injections.getIsInjected().equalsIgnoreCase("0") && (System.currentTimeMillis() >= ((getMilliFromDate(GetBaby.getInstance().getBirthday()) +
                (Long.parseLong(String.valueOf(Long.parseLong(injections.getDate()) *
                        Long.parseLong("" + (24 * 60 * 60 * 1000))))))))) {
            spinner.setSelection(0);
        } else if (injections.getIsInjected().equalsIgnoreCase("0") && (System.currentTimeMillis() < ((getMilliFromDate(GetBaby.getInstance().getBirthday()) +
                (Long.parseLong(String.valueOf(Long.parseLong(injections.getDate()) *
                        Long.parseLong("" + (24 * 60 * 60 * 1000))))))))) {
            spinner.setSelection(2);
        }
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                switch (position) {
                    case 0:
                        poss = 0;
                        break;
                    case 1:
                        poss = 1;
                        break;
                    case 2:
                        poss = 0;
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        edtDate.setOnClickListener(this);
        edtDate.setCursorVisible(false);
        edtDate.setFocusableInTouchMode(false);
        edtDate.setFocusable(false);
        mPresenter=new PresenterEditInjection();
        mPresenter.onAttach(this);
        back.setOnClickListener(this);
        imSave.setOnClickListener(this);
    }
    private void callApi() {
        String date = edtDate.getText().toString();
        String medicine = edtMedicine.getText().toString();
        String note = edtNote.getText().toString();
        mPresenter.editInjection(String.valueOf(GetBaby.getInstance().getBabyId()),
                injections.getId(),
                String.valueOf(User.getInstans().getId()), note, date, medicine, poss);
    }
    public void datePicker(final Context context, final EditText textView, final String type) {
        Calendar calendar = Calendar.getInstance();
        final SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
        DatePickerDialog datePickerDialog = new DatePickerDialog(context, R.style.DialogTheme, (view, year, monthOfYear, dayOfMonth) -> {
            Calendar newDate = Calendar.getInstance();
            newDate.set(year, monthOfYear, dayOfMonth);
            newDate.set(year, monthOfYear, dayOfMonth);
            textView.setText(dateFormatter.format(newDate.getTime()));

        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_injected:
                finish();
                break;
            case R.id.save_injected:
                callApi();
                break;
            case R.id.date:
                datePicker(this, edtDate, String.valueOf(R.style.DialogTheme));
                break;
        }
    }
    public void showDialog() {
        if (dialog == null) {
            dialog = new Dialog(EditInjectionsActivity.this);
        }
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                finish();
            }
        },2000);
        dialog.setContentView(R.layout.custom_dialog_edit_injected);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.show();

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }
    public long getMilliFromDate(String dateFormat) {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        try {
            date = formatter.parse(dateFormat);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println("Today is " + date);
        return date.getTime();
    }
    @Override
    public void editSuccess() {
        showDialog();
    }
    @Override
    public void onFail() {
        StyleableToast.makeText(this,getResources().getString(R.string.error),R.style.ColoredText).show();
    }
    @Override
    public void showToast() {
        StyleableToast.makeText(this,getResources().getString(R.string.toast),R.style.ColoredText).show();

    }
}

