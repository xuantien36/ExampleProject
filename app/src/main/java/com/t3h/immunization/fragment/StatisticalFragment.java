package com.t3h.immunization.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.t3h.immunization.R;
import com.t3h.immunization.activity.EditInjectionsActivity;
import com.t3h.immunization.adapter.ExpandableListAdapter;
import com.t3h.immunization.api.ApiBuilder;
import com.t3h.immunization.model.GetBaby;
import com.t3h.immunization.model.InjectionGroup;
import com.t3h.immunization.model.Injections;
import com.t3h.immunization.respone.ResponeStatistical;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StatisticalFragment extends Fragment {
    @BindView(R.id.expandableListView)
    ExpandableListView expandableListView;
    private ArrayList<Injections> arrayList;
    ExpandableListAdapter expandableListAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.statistical_fragment, container, false);
        ButterKnife.bind(this, view);
        arrayList = new ArrayList<>();
        return view;
    }
    public void callApi(){
        ApiBuilder.getInstance().getinjected(GetBaby.getInstance().getBabyId()).enqueue(new Callback<ResponeStatistical>() {
            @Override
            public void onResponse(Call<ResponeStatistical> call, Response<ResponeStatistical> response) {
                List<InjectionGroup> injectionGroup = response.body().getInjectionGroup();
                List<Injections> data = response.body().getData();
                arrayList.clear();
                arrayList.addAll(data);
                Log.e("STA", "onResponse: tttttt"+arrayList.size() );
//                for (InjectionGroup injectedGrp : injectionGroup) {
//                    if (injectedGrp.getGroupInjection().equalsIgnoreCase("4")) {
//                        Log.e("TAG", "GET INJeCTION: " + data.get(4).getId());
//                    }
//                }
                expandableListAdapter = new ExpandableListAdapter(getContext(), injectionGroup);
                expandableListAdapter.setDataStatis(injectionGroup, data);
                expandableListView.setAdapter(expandableListAdapter);
                expandableListAdapter.setChildListener(new ExpandableListAdapter.callBackChild() {
                    @Override
                    public void onclickChild(int position,String date,String name) {
                        Intent intent=new Intent(getContext(),EditInjectionsActivity.class);
                        intent.putExtra("child",date);
                        intent.putExtra("title",name);
                        startActivity(intent);
                        Toast.makeText(getContext(), ""+name, Toast.LENGTH_SHORT).show();
                    }
                });

            }
            @Override
            public void onFailure(Call<ResponeStatistical> call, Throwable t) {

            }
        });

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public void onResume() {
        super.onResume();
        callApi();
    }
}

