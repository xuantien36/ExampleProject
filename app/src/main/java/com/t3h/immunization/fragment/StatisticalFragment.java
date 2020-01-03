package com.t3h.immunization.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import com.t3h.immunization.R;
import com.t3h.immunization.adapter.StatisticalAdapter;
import com.t3h.immunization.model.Statistical;
import java.util.ArrayList;
import butterknife.BindView;
import butterknife.ButterKnife;

public class StatisticalFragment extends Fragment {
    private ArrayList<Statistical> data;
    private StatisticalAdapter adapter;
    @BindView(R.id.lv_statistical)
    RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.statistical_fragment, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        initData();
    }

    private void initData() {
        data = new ArrayList<>();
        data.add(new Statistical(R.drawable.group407,"Lao Phổi",0,0,0));
        data.add(new Statistical(R.drawable.group407,"Lao Phổi",0,0,0));
        data.add(new Statistical(R.drawable.group407,"Lao Phổi",0,0,0));
        data.add(new Statistical(R.drawable.group407,"Lao Phổi",0,0,0));
        data.add(new Statistical(R.drawable.group407,"Lao Phổi",0,0,0));
        data.add(new Statistical(R.drawable.group407,"Lao Phổi",0,0,0));
        data.add(new Statistical(R.drawable.group407,"Lao Phổi",0,0,0));
        data.add(new Statistical(R.drawable.group407,"Lao Phổi",0,0,0));
        data.add(new Statistical(R.drawable.group407,"Lao Phổi",0,0,0));
        data.add(new Statistical(R.drawable.group407,"Lao Phổi",0,0,0));
        data.add(new Statistical(R.drawable.group407,"Lao Phổi",0,0,0));
        data.add(new Statistical(R.drawable.group407,"Lao Phổi",0,0,0));
        data.add(new Statistical(R.drawable.group407,"Lao Phổi",0,0,0));
        adapter.setData(data);
    }
    private void initView() {
        adapter = new StatisticalAdapter(getContext());
        recyclerView.setAdapter(adapter);

    }
}
