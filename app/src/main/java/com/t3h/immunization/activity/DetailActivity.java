package com.t3h.immunization.activity;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.t3h.immunization.R;
import com.t3h.immunization.model.Vaccine;
import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity implements View.OnClickListener {
    private Vaccine vaccine;
    @BindView(R.id.immunization_schedule)
    TextView tv_schedule;
    @BindView(R.id.name)
    TextView tvName;
    @BindView(R.id.description)
    TextView tv_Description;
    @BindView(R.id.im_close_detail)
    ImageView imClose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        Intent intent = getIntent();
        vaccine = (Vaccine) intent.getSerializableExtra("data");
        tvName.setText(vaccine.getNameVaccine());
        tv_schedule.setText("-1 :"+vaccine.getVaccinationSchedule());
        tv_Description.setText(vaccine.getDescription());
        imClose.setOnClickListener(this);

    }
    @Override
    public void onClick(View view) {
        finish();

    }
}
