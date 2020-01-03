package com.t3h.immunization.activity;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import com.t3h.immunization.R;
import com.t3h.immunization.adapter.VaccineListAdapter;
import com.t3h.immunization.model.VaccineList;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class VaccineActivity extends AppCompatActivity {
    private ArrayList<VaccineList>data;
    private VaccineListAdapter adapter;
    @BindView(R.id.lv_vaccine)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vaccine);
        ButterKnife.bind(this);
        initView();
        intData();
    }

    private void intData() {
        data=new ArrayList<>();
        data.add(new VaccineList(R.drawable.ic_ellipse_114,"Tuberculosis"));
        data.add(new VaccineList(R.drawable.ic_ellipse_114,"Hepatits B"));
        data.add(new VaccineList(R.drawable.ic_ellipse_114,"Gastritis"));
        data.add(new VaccineList(R.drawable.ic_ellipse_114,"Diphtheria, Whooping Cough, Tetanus\n" +
                "Poliomyelitis, Meninggitis, Pharyngitis\n" +
                "Penuemonia.. D0 HIB"));
        data.add(new VaccineList(R.drawable.ic_ellipse_114,"Tuberculosis"));
        data.add(new VaccineList(R.drawable.ic_ellipse_114,"Tuberculosis"));
        data.add(new VaccineList(R.drawable.ic_ellipse_114,"Tuberculosis"));
        data.add(new VaccineList(R.drawable.ic_ellipse_114,"Tuberculosis"));
        data.add(new VaccineList(R.drawable.ic_ellipse_114,"Tuberculosis"));
        adapter.setData(data);
    }

    private void initView() {
        adapter=new VaccineListAdapter(this);
        recyclerView.setAdapter(adapter);
    }
}
