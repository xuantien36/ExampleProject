package com.t3h.immunization.fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.t3h.immunization.R;
import com.t3h.immunization.adapter.AdapterNotInjected;
import com.t3h.immunization.vacxin.model.InjectionGroup;
import com.t3h.immunization.statiscal.model.Injections;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;

public class NotinjectedFragment extends Fragment  {
    private AdapterNotInjected adapter;
    @BindView(R.id.expandableListView)
    ExpandableListView expandableList;
    List<InjectionGroup> groups;
    private List<List<Injections>> dataInjection;

    public NotinjectedFragment(List<List<Injections>> dataInjection, List<InjectionGroup>groups) {
        this.dataInjection = dataInjection;
        this.groups = groups;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.all_fragment, container, false);
        ButterKnife.bind(this, view);
        Log.e("TATTT", "onCreateView: "+dataInjection.size() );
        adapter = new AdapterNotInjected(getContext());
        adapter.setDataList(dataInjection,groups);
        expandableList.setAdapter(adapter);
        expandableList.setAdapter(adapter);
        for (int i = 0; i <dataInjection.size() ; i++) {
            expandableList.expandGroup(i);
        }
        expandableList.setOnGroupClickListener((parent, v, groupPosition, id) -> {
            return true; // This way the expander cannot be collapsed
        });
        return view;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}
