package com.t3h.immunization.fragment;

import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.t3h.immunization.R;
import com.t3h.immunization.adapter.ExpanAdapterInjected;
import com.t3h.immunization.adapter.VaccineBookAdapter;
import com.t3h.immunization.model.InjectionGroup;
import com.t3h.immunization.model.Injections;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;

public class AllFragment extends Fragment implements VaccineBookAdapter.ItemClickListener {
    private ExpanAdapterInjected adapter;
    @BindView(R.id.expandableListView)
    ExpandableListView recyclerView;
    List<InjectionGroup> groups;
    private List<List<Injections>> dataInjection;

    public AllFragment(List<List<Injections>> dataInjection, List<InjectionGroup>groups) {
        this.dataInjection = dataInjection;
        this.groups = groups;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.all_fragment, container, false);
        ButterKnife.bind(this, view);
//        section=new ArrayList<>();
//        ApiBuilder.getInstance().getinjected(GetBaby.getInstance().getBabyId()).enqueue(new Callback<ResponeStatistical>() {
//            @RequiresApi(api = Build.VERSION_CODES.N)
//            @Override
//            public void onResponse(Call<ResponeStatistical> call, Response<ResponeStatistical> response) {
//                List<InjectionGroup> injectionGroup  = response.body().getInjectionGroup();
//                List<Injections> data =response.body().getData();
//                Log.e("data", "onResponse 1111: "+response.body().getData().size());
//                Log.e("injected", "onResponse 2222: "+response.body().getInjectionGroup().size() );
//
////                calculatorSection(data);
////
////                groupDataInjection(data, section);
//
//                if (injectionGroup != null){
//
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ResponeStatistical> call, Throwable t) {
//
//            }
//        });
        Log.e("TATTT", "onCreateView: "+dataInjection.size() );
        adapter = new ExpanAdapterInjected(getContext());
        adapter.setDataList(dataInjection,groups);
        recyclerView.setAdapter(adapter);
        for (int i = 0; i <dataInjection.size() ; i++) {
            recyclerView.expandGroup(i);
        }
        recyclerView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int i) {

            }
        });
        return view;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    public String getDate(long milliSeconds, String dateFormat) {
        SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliSeconds);
        return formatter.format(calendar.getTime());
    }

    public long getMilliFromDate(String dateFormat) {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        try {
            date = formatter.parse(dateFormat);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println("Today is " + date);
        return date.getTime();

    }

    private void initData() {

    }
    @Override
    public void onClicked(int position) {
    }

    @Override
    public void onLongClicked(int position) {

    }
}
