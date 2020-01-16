package com.t3h.immunization.activity;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.t3h.immunization.R;
import butterknife.BindView;
import butterknife.ButterKnife;

public class StartsActivity extends AppCompatActivity {
    @BindView(R.id.btn_starst)
    Button btnStarts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starts);
        ButterKnife.bind(this);
        btnStarts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(StartsActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}
