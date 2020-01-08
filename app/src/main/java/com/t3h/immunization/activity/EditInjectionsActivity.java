package com.t3h.immunization.activity;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import com.t3h.immunization.R;
import com.t3h.immunization.api.ApiBuilder;
import com.t3h.immunization.model.ResponeInjections;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditInjectionsActivity extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.back_injected)
    ImageView back;
    @BindView(R.id.save_injected)
    ImageView imSave;
    @BindView(R.id.medicine)
    EditText edtMedicine;
    @BindView(R.id.note)
    EditText edtNote;
    @BindView(R.id.injected)
    EditText edtInjected;
    @BindView(R.id.date)
    EditText edtDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_information);
        ButterKnife.bind(this);
    }

    private void callApi() {
        String medicine =edtMedicine.getText().toString();
        String note =edtNote.getText().toString();
        String injected =edtInjected.getText().toString();
        String date =edtDate.getText().toString();
        ApiBuilder.getInstance().updateInjections("","","1",medicine,note,injected,date).enqueue(new Callback<ResponeInjections>() {
            @Override
            public void onResponse(Call<ResponeInjections> call, Response<ResponeInjections> response) {
                if (response.body().getStatus()==true){

                }else {
                    Toast.makeText(EditInjectionsActivity.this, "Error", Toast.LENGTH_SHORT).show();
                }

            }
            @Override
            public void onFailure(Call<ResponeInjections> call, Throwable t) {

            }
        });
        back.setOnClickListener(this);
        imSave.setOnClickListener(this);

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
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
