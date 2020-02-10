package com.t3h.immunization.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.t3h.immunization.utils.AppPreferences;
import com.t3h.immunization.utils.PrefManager;
import com.t3h.immunization.R;
import com.t3h.immunization.adapter.HomeAdapter;
import com.t3h.immunization.fragment.FirstFragment;
import com.t3h.immunization.fragment.FourFragment;
import com.t3h.immunization.fragment.SecondFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.t3h.immunization.utils.Constant.KEY_NEXT;

public class WelcomeActivity extends AppCompatActivity implements View.OnClickListener{
    private MyViewPagerAdapter myViewPagerAdapter;
    private TextView[] dots;
    private int[] layouts;
    private PrefManager prefManager;
    private HomeAdapter adapter;
    FirstFragment firstFragment = new FirstFragment();
    SecondFragment secondFragment = new SecondFragment();
    FourFragment fourFragment = new FourFragment();
    Fragment[] frm = {firstFragment, secondFragment, fourFragment};
    @BindView(R.id.viewpager)
    ViewPager viewPager;
    @BindView(R.id.layoutDots)
    LinearLayout dotsLayout;
    @BindView(R.id.btn_skip)
    ImageButton btnSkip;
    @BindView(R.id.btn_next)
    ImageButton btnNext;
    @BindView(R.id.btn_ignore)
    Button btnIgnore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        prefManager = new PrefManager(this);
        if (!prefManager.isFirstTimeLaunch()) {
            launchHomeScreen();
            finish();
        }
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }

        setContentView(R.layout.activity_welcome);
        ButterKnife.bind(this);
        init();
        adapter = new HomeAdapter(getSupportFragmentManager(), frm);
        viewPager.setAdapter(adapter);
        layouts = new int[]{
                R.layout.first_slide,
                R.layout.second_slide,
                R.layout.four_slide};
        addBottomDots(0);
        changeStatusBarColor();
        myViewPagerAdapter = new MyViewPagerAdapter();
        viewPager.addOnPageChangeListener(viewPagerPageChangeListener);

}
    private void init() {
        AppPreferences.getInstance(getApplicationContext()).putBoolean(KEY_NEXT, false);
        btnSkip.setOnClickListener(this);
        btnNext.setOnClickListener(this);
        btnIgnore.setOnClickListener(this);
    }

    private void addBottomDots(int currentPage) {
        dots = new TextView[layouts.length];

        int[] colorsActive = getResources().getIntArray(R.array.array_dot_active);
        int[] colorsInactive = getResources().getIntArray(R.array.array_dot_inactive);

        dotsLayout.removeAllViews();
        for (int i = 0; i < dots.length; i++) {
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(colorsInactive[currentPage]);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.setMargins(15, 0, 40, 0);
            dotsLayout.addView(dots[i], params);
        }

        if (dots.length > 0)
            dots[currentPage].setTextColor(colorsActive[currentPage]);
    }

    private int getItem(int i) {
        return viewPager.getCurrentItem() + i;
    }

    private void launchHomeScreen() {
        prefManager.setFirstTimeLaunch(false);
        startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
        finish();
    }
    ViewPager.OnPageChangeListener viewPagerPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageSelected(int position) {
            addBottomDots(position);
            btnSkip.setVisibility(View.VISIBLE);
            if (position == 0) {
                btnSkip.setVisibility(View.GONE);
                btnNext.setVisibility(View.VISIBLE);
            }
            if (position == layouts.length - 1) {
                btnNext.setVisibility(View.GONE);
                btnSkip.setVisibility(View.VISIBLE);
            } else {

            }
        }
        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {

        }

        @Override
        public void onPageScrollStateChanged(int arg0) {

        }
    };
    private void changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_skip:
                int current = getItem(-1);
                if (current < layouts.length) ;
                viewPager.setCurrentItem(current);
                break;
            case R.id.btn_next:
                int current_next = getItem(+1);
                if (current_next < layouts.length) {
                    viewPager.setCurrentItem(current_next);
                }
                break;
            case R.id.btn_ignore:
                Intent intent=new Intent(this,StartsActivity.class);
                startActivity(intent);
                break;
        }

    }
    public class MyViewPagerAdapter extends PagerAdapter {
        private LayoutInflater layoutInflater;

        public MyViewPagerAdapter() {
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View view = layoutInflater.inflate(layouts[position], container, false);
            container.addView(view);

            return view;
        }

        @Override
        public int getCount() {
            return layouts.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }


        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            View view = (View) object;
            container.removeView(view);
        }
    }
}
