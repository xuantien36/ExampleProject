package com.t3h.immunization.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class HomeAdapter extends FragmentStatePagerAdapter {
    private Fragment[] frmData;

    public HomeAdapter(@NonNull FragmentManager fm,Fragment[] frmData) {
        super(fm);
        this.frmData=frmData;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return frmData[position];
    }

    @Override
    public int getCount() {
        return frmData == null ? 0 : frmData.length;
    }
}
