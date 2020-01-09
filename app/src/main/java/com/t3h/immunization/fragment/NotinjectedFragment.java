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
import com.t3h.immunization.adapter.VaccineBookAdapter;
import com.t3h.immunization.api.ApiBuilder;
import com.t3h.immunization.model.GetBaby;
import com.t3h.immunization.model.InjectionGroup;
import com.t3h.immunization.respone.ResponeStatistical;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NotinjectedFragment extends Fragment implements VaccineBookAdapter.ItemClickListener {
    private ArrayList<InjectionGroup> data;
    private VaccineBookAdapter adapter;
    @BindView(R.id.lv_all)
    RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.all_fragment, container, false);
        ButterKnife.bind(this, view);
        ApiBuilder.getInstance().getinjected(GetBaby.getInstance().getBabyId()).enqueue(new Callback<ResponeStatistical>() {
            @Override
            public void onResponse(Call<ResponeStatistical> call, Response<ResponeStatistical> response) {
                List<InjectionGroup> injectionGroup = response.body().getInjectionGroup();
                if (injectionGroup!=null){
                    adapter.setData((ArrayList<InjectionGroup>) injectionGroup);
                }

            }

            @Override
            public void onFailure(Call<ResponeStatistical> call, Throwable t) {

            }
        });
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();

    }
    private void initView() {
        adapter = new VaccineBookAdapter(getContext());
        recyclerView.setAdapter(adapter);
        adapter.setOnListener(this);

    }
    @Override
    public void onClicked(int position) {

    }

    @Override
    public void onLongClicked(int position) {

    }
}
