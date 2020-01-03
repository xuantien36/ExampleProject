package com.t3h.immunization.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class PagerTabAdapter extends FragmentStatePagerAdapter {

    private Fragment[] frmData;

    public PagerTabAdapter(@NonNull FragmentManager fm, Fragment[] frmData) {
        super(fm);
        this.frmData = frmData;
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

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Tất Cả ";
            case 1:
                return "Đã Tiêm";
            case 2:
                return "Chưa Tiêm";
            case 3:
                return "Bỏ Lỡ";

        }
        return getPageTitle(position);

    }
}

