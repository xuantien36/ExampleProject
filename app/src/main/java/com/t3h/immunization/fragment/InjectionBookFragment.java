package com.t3h.immunization.fragment;
import android.app.ProgressDialog;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import com.google.android.material.tabs.TabLayout;
import com.t3h.immunization.R;
import com.t3h.immunization.adapter.PagerTabAdapter;
import com.t3h.immunization.api.ApiBuilder;
import com.t3h.immunization.model.GetBaby;
import com.t3h.immunization.respone.ResponeStatistical;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InjectionBookFragment extends Fragment implements ViewPager.OnPageChangeListener {
    private PagerTabAdapter adapter;
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @BindView(R.id.baby_name)
    TextView babyName;
    private Handler handler=new Handler();
    private ProgressDialog progressDialog;

    private void initView() {
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Loading...");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();
        ApiBuilder.getInstance().getinjected(GetBaby.getInstance().getBabyId()).enqueue(new Callback<ResponeStatistical>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call<ResponeStatistical> call, Response<ResponeStatistical> response) {
                if (response.body().getStatus()==true){
                    adapter = new PagerTabAdapter(getActivity().getSupportFragmentManager(),response.body().getData(),response.body().getInjectionGroup());
                    viewPager.setAdapter(adapter);
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            progressDialog.dismiss();

                        }
                    },1000);
                    viewPager.setCurrentItem(0);
                    viewPager.setOffscreenPageLimit(4);
                    tabLayout.setupWithViewPager(viewPager);
                    babyName.setText(GetBaby.getInstance().getName());
                }
            }

            @Override
            public void onFailure(Call<ResponeStatistical> call, Throwable t) {

            }
        });

    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.injectionbook_fragment,container,false);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }
    @Override
    public void onPageSelected(int position) {

    }
    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onResume() {
        super.onResume();
        if (GetBaby.getInstance().getBabyId()!=null){
            initView();
        }else {
            Toast.makeText(getContext(), "Không có gì để hiển thị", Toast.LENGTH_SHORT).show();
        }
    }
}
