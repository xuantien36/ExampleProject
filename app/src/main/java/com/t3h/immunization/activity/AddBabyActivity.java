package com.t3h.immunization.activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.t3h.immunization.R;
import com.t3h.immunization.api.ApiBuilder;
import com.t3h.immunization.model.User;
import com.t3h.immunization.respone.ResponeRegister;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddBabyActivity extends AppCompatActivity implements View.OnClickListener {
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
    String checkedBox ;


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
            Toast.makeText(AddBabyActivity.this, "" + checkedBox, Toast.LENGTH_SHORT).show();

        });
        imBack.setOnClickListener(this);
        imSave.setOnClickListener(this);
        edtBirthday.setOnClickListener(this);
        edtBirthday.setCursorVisible(false);
        edtBirthday.setFocusableInTouchMode(false);
        edtBirthday.setFocusable(false);
    }

    public void addBaby() {
        String name = edtName.getText().toString();
        String birthday = edtBirthday.getText().toString();
        String note = edtNote.getText().toString();

        if (name.equals("")||birthday.equals("")||note.equals("")|| checkedBox.equals("")){
            Toast.makeText(AddBabyActivity.this, "Điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
        }else {
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
                datePicker(this,edtBirthday, String.valueOf(R.style.DialogTheme));
                break;
        }

    }
    public void showDialog() {
        dialog = new Dialog(this);
        dialog.setContentView(R.layout.custom_dialog_add);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (dialog!=null){
            dialog.dismiss();
        }
    }
}
