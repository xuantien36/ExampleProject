package com.t3h.immunization.activity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.annotation.VisibleForTesting;
import androidx.appcompat.app.AppCompatActivity;

import com.muddzdev.styleabletoast.StyleableToast;
import com.t3h.immunization.R;
import com.t3h.immunization.api.ApiBuilder;
import com.t3h.immunization.model.User;
import com.t3h.immunization.respone.ResponeRegister;
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

public class AddBabyActivity extends AppCompatActivity implements View.OnClickListener, DatePickerDialog.OnDateSetListener, DialogInterface.OnCancelListener, com.tsongkha.spinnerdatepicker.DatePickerDialog.OnDateSetListener, com.tsongkha.spinnerdatepicker.DatePickerDialog.OnDateCancelListener {
    @BindView(R.id.back)
    ImageView imBack;
    @BindView(R.id.save)
    ImageView imSave;
    @BindView(R.id.edt_name_baby)
    EditText edtName;
    @BindView(R.id.edt_add_birthday)
    EditText edtBirthday;
    @BindView(R.id.edt_note)
    EditText edtNote;
    @BindView(R.id.radio_male)
    RadioButton male;
    @BindView(R.id.radio_female)
    RadioButton female;
    private Dialog dialog;
    @BindView(R.id.radio_group)
    RadioGroup group;
    String checkedBox;
    private DatePickerDialog datePickerDialog;
    SimpleDateFormat simpleDateFormat;
    @BindView(R.id.avatar_add)
    ImageView imAvatar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_baby);
        ButterKnife.bind(this);
        init();
    }
    private void init() {
        group.setOnCheckedChangeListener((radioGroup, i) -> {
            int checkedRadio = radioGroup.getCheckedRadioButtonId();
            RadioButton checkedRadioButton = findViewById(checkedRadio);
            checkedBox = checkedRadioButton.getText().toString();
            if (checkedBox.equalsIgnoreCase("Nam")){
                imAvatar.setImageResource(R.drawable.group_730);
            }else {
                imAvatar.setImageResource(R.drawable.group_731);
            }
            StyleableToast.makeText(this, ""+checkedBox,R.style.ColoredText).show();
        });
        imBack.setOnClickListener(this);
        imSave.setOnClickListener(this);
        edtBirthday.setOnClickListener(this);
        edtBirthday.setCursorVisible(false);
        edtBirthday.setFocusableInTouchMode(false);
        edtBirthday.setFocusable(false);
        simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.US);

    }

    public void addBaby() {
        String name = edtName.getText().toString();
        String birthday = edtBirthday.getText().toString();
        String note = edtNote.getText().toString();
        if (name.equals("") || birthday.equals("")|| !male.isChecked() && !female.isChecked()) {
            StyleableToast.makeText(AddBabyActivity.this, getResources().getString(R.string.toast),R.style.ColoredText).show();

        } else {
            ApiBuilder.getInstance().addBaby(User.getInstans().getId(), name, checkedBox, birthday, "", note,
                    true).enqueue(new Callback<ResponeRegister>() {
                @Override
                public void onResponse(Call<ResponeRegister> call, Response<ResponeRegister> response) {
                    if (response.body().getStatus() == true) {
                        showDialog();
                        finish();
                    }
                }
                @Override
                public void onFailure(Call<ResponeRegister> call, Throwable t) {

                }
            });
        }
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

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.save:
                addBaby();
                break;
            case R.id.edt_add_birthday:
                showDate(2020, 01, 02, R.style.DatePickerSpinner);
//                datePicker(this, edtBirthday, String.valueOf(R.style.DialogTheme));
                break;
        }

    }
    public void showDialog() {
        if (dialog == null) {
            dialog = new Dialog(AddBabyActivity.this);
        }
        dialog.setContentView(R.layout.custom_dialog_add);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.show();

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (dialog != null) {
            dialog.dismiss();
        }
    }

    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {


    }

    @Override
    public void onCancel(DialogInterface dialogInterface) {

    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @VisibleForTesting
    public void showDate(int year, int monthOfYear, int dayOfMonth, int spinnerTheme) {
        new SpinnerDatePickerDialogBuilder()
                .context(AddBabyActivity.this)
                .callback(AddBabyActivity.this)
                .onCancel(AddBabyActivity.this)
                .spinnerTheme(spinnerTheme)
                .defaultDate(year, monthOfYear, dayOfMonth)
                .build()
                .show();
    }

    @Override
    public void onDateSet(com.tsongkha.spinnerdatepicker.DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        Calendar calendar = new GregorianCalendar(year, monthOfYear, dayOfMonth);
        edtBirthday.setText(simpleDateFormat.format(calendar.getTime()));

    }

    @Override
    public void onCancelled(com.tsongkha.spinnerdatepicker.DatePicker view) {
        edtBirthday.setText("");

    }
}
