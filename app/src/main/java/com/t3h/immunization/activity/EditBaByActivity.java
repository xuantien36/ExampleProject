package com.t3h.immunization.activity;
import androidx.appcompat.app.AppCompatActivity;
import android.app.Dialog;
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
import com.t3h.immunization.respone.ResponeRegister;
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
    }
    public void editBaby() {
        String name = edtName_Edit.getText().toString();
        String note = edtNote.getText().toString();
        String birthday =editBirthday.getText().toString();
        ApiBuilder.getInstance().editBaby(1, baby.getBabyId(), name,checkedBox,birthday, "", note).enqueue(new Callback<ResponeRegister>() {
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
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (dialog!=null){
            dialog.dismiss();
        }
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
        }

    }
    public void showDialog() {
        dialog = new Dialog(this);
        dialog.setContentView(R.layout.custom_dialog_edit);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.show();

    }
}
