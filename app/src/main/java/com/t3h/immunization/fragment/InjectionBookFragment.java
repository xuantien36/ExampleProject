package com.t3h.immunization.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.t3h.immunization.R;
import com.t3h.immunization.adapter.PagerTabAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class InjectionBookFragment extends Fragment implements ViewPager.OnPageChangeListener {
    private PagerTabAdapter adapter;
    private AllFragment allFragment = new AllFragment();
    private AboutInjectFragment about = new AboutInjectFragment();
    private HaveInjectedFragment passedFragment = new HaveInjectedFragment();
    private NotinjectedFragment notinjectedFragment=new NotinjectedFragment();

    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.view_pager)
    ViewPager viewPager;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
    }

    private void initView() {
        Fragment[] frm = {allFragment,passedFragment, about,notinjectedFragment};
        adapter = new PagerTabAdapter(getChildFragmentManager(), frm);
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(0);
        viewPager.setOffscreenPageLimit(4);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.addOnPageChangeListener(this);
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
}
