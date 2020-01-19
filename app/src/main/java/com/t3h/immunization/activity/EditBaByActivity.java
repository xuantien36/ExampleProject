package com.t3h.immunization.activity;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import com.t3h.immunization.R;
import com.t3h.immunization.api.ApiBuilder;
import com.t3h.immunization.model.GetBaby;
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

public class EditBaByActivity extends AppCompatActivity implements View.OnClickListener {
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_ba_by);
        ButterKnife.bind(this);
        init();
        Intent intent = getIntent();
        baby = (GetBaby) intent.getSerializableExtra("data");
        edtName_Edit.setText(baby.getName());
        edtNote.setText(baby.getNote());
        editBirthday.setText(baby.getBirthday());
        if (baby.getGender().equalsIgnoreCase("Nam")){
            rdioMale.setChecked(true);
        }else {
            rdioFemale.setChecked(true);
        }

    }
    public void editBaby() {
        String name = edtName_Edit.getText().toString();
        String note = edtNote.getText().toString();
        String birthday =editBirthday.getText().toString();
        ApiBuilder.getInstance().editBaby(User.getInstans().getId(), baby.getBabyId(), name,checkedBox,birthday, "", note).enqueue(new Callback<ResponeRegister>() {
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
    private void init() {
        group.setOnCheckedChangeListener((radioGroup, i) -> {
            int checkedRadio = radioGroup.getCheckedRadioButtonId();
            RadioButton checkedRadioButton = findViewById(checkedRadio);
            checkedBox = checkedRadioButton.getText().toString();
            Toast.makeText(EditBaByActivity.this, "" + checkedBox, Toast.LENGTH_SHORT).show();

        });
        imBack.setOnClickListener(this);
        imSave.setOnClickListener(this);
        editBirthday.setOnClickListener(this);
        editBirthday.setCursorVisible(false);
        editBirthday.setFocusableInTouchMode(false);
        editBirthday.setFocusable(false);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (dialog!=null){
            dialog.dismiss();
        }
    }
    public void datePicker(final Context context, final EditText textView, final String type) {
        Calendar calendar = Calendar.getInstance();
        final SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
        DatePickerDialog datePickerDialog = new DatePickerDialog(context, R.style.DialogTheme, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(android.widget.DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                newDate.set(year, monthOfYear, dayOfMonth);
                textView.setText(dateFormatter.format(newDate.getTime()));

            }

        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

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
                datePicker(this,editBirthday, String.valueOf(R.style.DialogTheme));
                break;
        }

    }
    public void showDialog() {
        dialog = new Dialog(this);
        dialog.setContentView(R.layout.custom_dialog_edit);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.show();

    }
}
