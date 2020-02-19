package com.t3h.immunization.activity;

import androidx.annotation.RequiresApi;
import androidx.annotation.VisibleForTesting;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.muddzdev.styleabletoast.StyleableToast;
import com.t3h.immunization.R;
import com.t3h.immunization.api.ApiBuilder;
import com.t3h.immunization.baby.model.GetBaby;
import com.t3h.immunization.basemvp.BaseActivity;
import com.t3h.immunization.editbaby.presenter.PresenterEditBaby;
import com.t3h.immunization.editbaby.view.EditBabyView;
import com.t3h.immunization.login.model.User;
import com.t3h.immunization.respone.ResponeRegister;
import com.tsongkha.spinnerdatepicker.DatePicker;
import com.tsongkha.spinnerdatepicker.SpinnerDatePickerDialogBuilder;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditBaByActivity extends BaseActivity<PresenterEditBaby> implements View.OnClickListener,
        com.tsongkha.spinnerdatepicker.DatePickerDialog.OnDateSetListener,
        com.tsongkha.spinnerdatepicker.DatePickerDialog.OnDateCancelListener, EditBabyView {
    @BindView(R.id.back_edit)
    ImageView imBack;
    @BindView(R.id.save_edit)
    ImageView imSave;
    @BindView(R.id.edit_name_baby)
    EditText edtName_Edit;
    @BindView(R.id.edit_note)
    EditText edtNote;
    private GetBaby baby;
    @BindView(R.id.radio_group_edit)
    RadioGroup group;
    String checkedBox;
    @BindView(R.id.edit_birthday)
    EditText editBirthday;
    private Dialog dialog;
    @BindView(R.id.radio_male)
    RadioButton rdioMale;
    @BindView(R.id.radio_female)
    RadioButton rdioFemale;
    SimpleDateFormat simpleDateFormat;
    @BindView(R.id.image_avatar)
    ImageView imAvatar;
    private Handler handler =new Handler();


    @Override
    protected PresenterEditBaby loadPresenter() {
        return new PresenterEditBaby();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        Intent intent = getIntent();
        baby = (GetBaby) intent.getSerializableExtra("data");
        edtName_Edit.setText(baby.getName());
        edtNote.setText(baby.getNote());
        editBirthday.setText(baby.getBirthday());
        if (baby.getGender().equalsIgnoreCase("Nam")) {
            rdioMale.setChecked(true);
        } else {
            rdioFemale.setChecked(true);
        }
        group.setOnCheckedChangeListener((radioGroup, i) -> {
            int checkedRadio = radioGroup.getCheckedRadioButtonId();
            RadioButton checkedRadioButton = findViewById(checkedRadio);
            checkedBox = checkedRadioButton.getText().toString();
            if (checkedBox.equalsIgnoreCase("Nam")) {
                imAvatar.setImageResource(R.drawable.group_730);
            } else {
                imAvatar.setImageResource(R.drawable.group_731);
            }
        });
        imBack.setOnClickListener(this);
        imSave.setOnClickListener(this);
        editBirthday.setOnClickListener(this);
        editBirthday.setCursorVisible(false);
        editBirthday.setFocusableInTouchMode(false);
        editBirthday.setFocusable(false);
        simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
        mPresenter.onAttach(this);

    }
    @Override
    protected int getLayoutId() {
        return R.layout.activity_edit_ba_by;
    }
    public void editBaby() {
        String name = edtName_Edit.getText().toString();
        String note = edtNote.getText().toString();
        String birthday = editBirthday.getText().toString();
        mPresenter.onEditBaby(User.getInstans().getId(), baby.getBabyId(), name, checkedBox, birthday, "", note);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (dialog != null) {
            dialog.dismiss();
        }
    }
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_edit:
                finish();
                break;
            case R.id.save_edit:
                editBaby();
                break;
            case R.id.edit_birthday:
                showDate(2020, 01, 02, R.style.DatePickerSpinner);
                break;
        }

    }
    public void showDialog() {
        if (dialog == null) {
            dialog = new Dialog(EditBaByActivity.this);
        }
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                finish();

            }
        },2000);
        dialog = new Dialog(this);
        dialog.setContentView(R.layout.custom_dialog_edit);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.show();

    }
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @VisibleForTesting
    public void showDate(int year, int monthOfYear, int dayOfMonth, int spinnerTheme) {
        new SpinnerDatePickerDialogBuilder()
                .context(EditBaByActivity.this)
                .callback(EditBaByActivity.this)
                .onCancel(EditBaByActivity.this)
                .spinnerTheme(spinnerTheme)
                .defaultDate(year, monthOfYear, dayOfMonth)
                .build()
                .show();
    }
    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        Calendar calendar = new GregorianCalendar(year, monthOfYear, dayOfMonth);
        editBirthday.setText(simpleDateFormat.format(calendar.getTime()));
    }
    @Override
    public void onCancelled(DatePicker view) {
        editBirthday.setText("");
    }
    @Override
    public void editSuccess() {
        showDialog();
    }
    @Override
    public void onFail() {
        StyleableToast.makeText(this, getResources().getString(R.string.error), R.style.ColoredText).show();

    }
    @Override
    public void showToast() {
        StyleableToast.makeText(this, getResources().getString(R.string.toast), R.style.ColoredText).show();
    }
}
