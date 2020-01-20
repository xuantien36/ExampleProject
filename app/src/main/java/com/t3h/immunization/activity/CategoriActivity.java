package com.t3h.immunization.activity;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.t3h.immunization.R;
import com.t3h.immunization.customize.ItemBottomBar;
import com.t3h.immunization.fragment.BabyFragment;
import com.t3h.immunization.fragment.InjectionBookFragment;
import com.t3h.immunization.fragment.OtherFragment;
import com.t3h.immunization.fragment.StatisticalFragment;
import com.t3h.immunization.fragment.VaccineFragment;
import com.t3h.immunization.util.AppPreferences;
import com.t3h.immunization.util.Constant;
import com.t3h.immunization.util.SaveData;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.t3h.immunization.util.Constant.KEY_LOGIN;
import static com.t3h.immunization.util.Constant.TAB1;
import static com.t3h.immunization.util.Constant.TAB2;
import static com.t3h.immunization.util.Constant.TAB3;
import static com.t3h.immunization.util.Constant.TAB4;
import static com.t3h.immunization.util.Constant.TAB5;

public class CategoriActivity extends AppCompatActivity  {
    @BindView(R.id.ivImage1)
    ItemBottomBar ivImage1;
    @BindView(R.id.ivImage2)
    ItemBottomBar ivImage2;
    @BindView(R.id.ivImage3)
    ItemBottomBar ivImage3;
    @BindView(R.id.ivImage4)
    ItemBottomBar ivImage4;
    @BindView(R.id.ivImage5)
    ItemBottomBar ivImage5;

    public int currentFragmentIndex = Constant.TAB1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SaveData.updateLangua(this);
        setContentView(R.layout.activity_categori);
        ButterKnife.bind(this);
        AppPreferences.getInstance(getApplicationContext()).putBoolean(KEY_LOGIN,true);
        getSupportFragmentManager().beginTransaction().replace(R.id.main_container, new BabyFragment()).commit();
    }

    private void activeTab(int index) {
        ivImage1.setActiveMode(false);
        ivImage2.setActiveMode(false);
        ivImage3.setActiveMode(false);
        ivImage4.setActiveMode(false);
        ivImage5.setActiveMode(false);

        if (index == Constant.TAB1) {
            ivImage1.setActiveMode(true);
        } else if (index == TAB2) {
            ivImage2.setActiveMode(true);
        } else if (index == Constant.TAB3) {
            ivImage3.setActiveMode(true);
        } else if (index == Constant.TAB4) {
            ivImage4.setActiveMode(true);
        } else if (index == TAB5) {
            ivImage5.setActiveMode(true);

        }
    }
    @OnClick({R.id.ivImage1, R.id.ivImage2, R.id.ivImage3, R.id.ivImage4, R.id.ivImage5})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ivImage1:
                this.activeTab(Constant.TAB1);
                changeTabBottom(Constant.TAB1);
                break;
            case R.id.ivImage2:
                this.activeTab(TAB2);
                changeTabBottom(TAB2);
                break;
            case R.id.ivImage3:
                this.activeTab(Constant.TAB3);
                changeTabBottom(Constant.TAB3);
                break;
            case R.id.ivImage4:
                this.activeTab(Constant.TAB4);
                changeTabBottom(Constant.TAB4);
                break;

            case R.id.ivImage5:
                this.activeTab(TAB5);
                changeTabBottom(TAB5);
                break;
        }
    }
    public void changeTabBottom(int index) {
        try {
            Fragment fragment = null;
            switch (index) {
                case TAB1:
                    fragment = new VaccineFragment();

                    break;
                case TAB2:
                    fragment = new StatisticalFragment();
                    break;
                case TAB3:
                    fragment = new BabyFragment();
                    break;
                case TAB4:
                    fragment = new InjectionBookFragment();
                    break;
                case TAB5:
                    fragment = new OtherFragment();
                    break;
            }

            this.activeTab(index);
            getSupportFragmentManager().beginTransaction().replace(R.id.main_container, fragment).commit();
            this.currentFragmentIndex = index;
        } catch (Exception e) {
        }
    }
    @Override
    protected void onRestart() {
        super.onRestart();

    }

}

