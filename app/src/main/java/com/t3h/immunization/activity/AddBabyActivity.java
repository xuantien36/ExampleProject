package com.t3h.immunization.activity;
import android.app.Dialog;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.t3h.immunization.R;
import com.t3h.immunization.api.ApiBuilder;
import com.t3h.immunization.model.ResponeRegister;
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
    @BindView(R.id.edt_birthday)
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
    private Handler handler=new Handler();

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
    }

    public void addBaby() {
        String name = edtName.getText().toString();
        String birthday = edtBirthday.getText().toString();
        String note = edtNote.getText().toString();
        if (name.equals("")||birthday.equals("")){
            Toast.makeText(AddBabyActivity.this, "Điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
        }else {
            ApiBuilder.getInstance().addBaby(1, name, checkedBox, birthday, "", note,
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
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.save:
                addBaby();
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
