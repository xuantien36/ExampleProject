package com.t3h.immunization.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import com.t3h.immunization.R;
import com.t3h.immunization.activity.DetailActivity;
import com.t3h.immunization.adapter.VaccineBookAdapter;
import com.t3h.immunization.model.VaccinationBook;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HaveInjectedFragment extends Fragment implements VaccineBookAdapter.ItemClickListener {
    private ArrayList<VaccinationBook> data;
    private VaccineBookAdapter adapter;
    @BindView(R.id.lv_passed)
    RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.passed_fragment, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        initData();
    }

    private void initData() {
        data = new ArrayList<>();
        data.add(new VaccinationBook("Tháng 10 Năm 2019", "Mũi\n1/1", "Tuberculosis",
                "28/10/2019", "1 tháng 27 ngày", R.drawable.ic_ellipse_202,"Newborn","Tuberculosis (TB) is an infectious disease usually caused by Mycobacterium tuberculosis (MTB) bacteria.\" +\n" +
                "                        \"[1] Tuberculosis generally affects the lungs, but can also affect other parts of the body.\" +\n" +
                "                        \"[1] Most infections do not have symptoms, in which case it is known as latent tuberculosis.\" +\n" +
                "                        \"[1] About 10% of latent infections progress to active disease which, if left untreated, kills about half of those affected.\" +\n" +
                "                        \"[1] The classic symptoms of active TB are a chronic cough with blood-containing mucus, fever, night sweats, and weight loss.\" +\n" +
                "                        \"[1] It was historically called \\\"consumption\\\" due to the weight loss.[8] Infection of other organs can cause a wide range of symptoms.[9]\\n\" +\n" +
                "                        \"\\n\" +\n" +
                "                        \"Tuberculosis is spread through the air when people who have active TB in their lungs cough, spit, speak, or sneeze.\" +\n" +
                "                        \"[1][10] People with latent TB do not spread the disease.[1] Active infection occurs more often in people with HIV/AIDS and in those who smoke.\" +\n" +
                "                        \"[1] Diagnosis of active TB is based on chest X-rays, as well as microscopic examination and culture of body fluids.\" +\n" +
                "                        \"[11] Diagnosis of latent TB relies on the tuberculin skin test (TST) or blood tests.[11]"));
        data.add(new VaccinationBook("Tháng 10 Năm 2019", "Mũi\n1/1", "Tuberculosis", "28/10/2019", "1 tháng 27 ngày", R.drawable.ic_ellipse_202,"Newborn","Tuberculosis (TB) is an infectious disease usually caused by Mycobacterium tuberculosis (MTB) bacteria.\" +\n" +
                "                        \"[1] Tuberculosis generally affects the lungs, but can also affect other parts of the body.\" +\n" +
                "                        \"[1] Most infections do not have symptoms, in which case it is known as latent tuberculosis.\" +\n" +
                "                        \"[1] About 10% of latent infections progress to active disease which, if left untreated, kills about half of those affected.\" +\n" +
                "                        \"[1] The classic symptoms of active TB are a chronic cough with blood-containing mucus, fever, night sweats, and weight loss.\" +\n" +
                "                        \"[1] It was historically called \\\"consumption\\\" due to the weight loss.[8] Infection of other organs can cause a wide range of symptoms.[9]\\n\" +\n" +
                "                        \"\\n\" +\n" +
                "                        \"Tuberculosis is spread through the air when people who have active TB in their lungs cough, spit, speak, or sneeze.\" +\n" +
                "                        \"[1][10] People with latent TB do not spread the disease.[1] Active infection occurs more often in people with HIV/AIDS and in those who smoke.\" +\n" +
                "                        \"[1] Diagnosis of active TB is based on chest X-rays, as well as microscopic examination and culture of body fluids.\" +\n" +
                "                        \"[11] Diagnosis of latent TB relies on the tuberculin skin test (TST) or blood tests.[11]"));
        data.add(new VaccinationBook("Tháng 10 Năm 2019", "Mũi\n1/1", "Tuberculosis", "28/10/2019", "1 tháng 27 ngày", R.drawable.ic_ellipse_202,"Newborn","Tuberculosis (TB) is an infectious disease usually caused by Mycobacterium tuberculosis (MTB) bacteria.\" +\n" +
                "                        \"[1] Tuberculosis generally affects the lungs, but can also affect other parts of the body.\" +\n" +
                "                        \"[1] Most infections do not have symptoms, in which case it is known as latent tuberculosis.\" +\n" +
                "                        \"[1] About 10% of latent infections progress to active disease which, if left untreated, kills about half of those affected.\" +\n" +
                "                        \"[1] The classic symptoms of active TB are a chronic cough with blood-containing mucus, fever, night sweats, and weight loss.\" +\n" +
                "                        \"[1] It was historically called \\\"consumption\\\" due to the weight loss.[8] Infection of other organs can cause a wide range of symptoms.[9]\\n\" +\n" +
                "                        \"\\n\" +\n" +
                "                        \"Tuberculosis is spread through the air when people who have active TB in their lungs cough, spit, speak, or sneeze.\" +\n" +
                "                        \"[1][10] People with latent TB do not spread the disease.[1] Active infection occurs more often in people with HIV/AIDS and in those who smoke.\" +\n" +
                "                        \"[1] Diagnosis of active TB is based on chest X-rays, as well as microscopic examination and culture of body fluids.\" +\n" +
                "                        \"[11] Diagnosis of latent TB relies on the tuberculin skin test (TST) or blood tests.[11]"));
        data.add(new VaccinationBook("Tháng 10 Năm 2019", "Mũi\n1/1", "Tuberculosis", "28/10/2019", "1 tháng 27 ngày", R.drawable.ic_ellipse_202,"Newborn","Tuberculosis (TB) is an infectious disease usually caused by Mycobacterium tuberculosis (MTB) bacteria.\" +\n" +
                "                        \"[1] Tuberculosis generally affects the lungs, but can also affect other parts of the body.\" +\n" +
                "                        \"[1] Most infections do not have symptoms, in which case it is known as latent tuberculosis.\" +\n" +
                "                        \"[1] About 10% of latent infections progress to active disease which, if left untreated, kills about half of those affected.\" +\n" +
                "                        \"[1] The classic symptoms of active TB are a chronic cough with blood-containing mucus, fever, night sweats, and weight loss.\" +\n" +
                "                        \"[1] It was historically called \\\"consumption\\\" due to the weight loss.[8] Infection of other organs can cause a wide range of symptoms.[9]\\n\" +\n" +
                "                        \"\\n\" +\n" +
                "                        \"Tuberculosis is spread through the air when people who have active TB in their lungs cough, spit, speak, or sneeze.\" +\n" +
                "                        \"[1][10] People with latent TB do not spread the disease.[1] Active infection occurs more often in people with HIV/AIDS and in those who smoke.\" +\n" +
                "                        \"[1] Diagnosis of active TB is based on chest X-rays, as well as microscopic examination and culture of body fluids.\" +\n" +
                "                        \"[11] Diagnosis of latent TB relies on the tuberculin skin test (TST) or blood tests.[11]"));
        data.add(new VaccinationBook("Tháng 10 Năm 2019", "Mũi\n1/1", "Tuberculosis", "28/10/2019", "1 tháng 27 ngày", R.drawable.ic_ellipse_202,"Newborn","Tuberculosis (TB) is an infectious disease usually caused by Mycobacterium tuberculosis (MTB) bacteria.\" +\n" +
                "                        \"[1] Tuberculosis generally affects the lungs, but can also affect other parts of the body.\" +\n" +
                "                        \"[1] Most infections do not have symptoms, in which case it is known as latent tuberculosis.\" +\n" +
                "                        \"[1] About 10% of latent infections progress to active disease which, if left untreated, kills about half of those affected.\" +\n" +
                "                        \"[1] The classic symptoms of active TB are a chronic cough with blood-containing mucus, fever, night sweats, and weight loss.\" +\n" +
                "                        \"[1] It was historically called \\\"consumption\\\" due to the weight loss.[8] Infection of other organs can cause a wide range of symptoms.[9]\\n\" +\n" +
                "                        \"\\n\" +\n" +
                "                        \"Tuberculosis is spread through the air when people who have active TB in their lungs cough, spit, speak, or sneeze.\" +\n" +
                "                        \"[1][10] People with latent TB do not spread the disease.[1] Active infection occurs more often in people with HIV/AIDS and in those who smoke.\" +\n" +
                "                        \"[1] Diagnosis of active TB is based on chest X-rays, as well as microscopic examination and culture of body fluids.\" +\n" +
                "                        \"[11] Diagnosis of latent TB relies on the tuberculin skin test (TST) or blood tests.[11]"));
        data.add(new VaccinationBook("Tháng 10 Năm 2019", "Mũi\n1/1", "Tuberculosis", "28/10/2019", "1 tháng 27 ngày", R.drawable.ic_ellipse_202,"Newborn","Tuberculosis (TB) is an infectious disease usually caused by Mycobacterium tuberculosis (MTB) bacteria.\" +\n" +
                "                        \"[1] Tuberculosis generally affects the lungs, but can also affect other parts of the body.\" +\n" +
                "                        \"[1] Most infections do not have symptoms, in which case it is known as latent tuberculosis.\" +\n" +
                "                        \"[1] About 10% of latent infections progress to active disease which, if left untreated, kills about half of those affected.\" +\n" +
                "                        \"[1] The classic symptoms of active TB are a chronic cough with blood-containing mucus, fever, night sweats, and weight loss.\" +\n" +
                "                        \"[1] It was historically called \\\"consumption\\\" due to the weight loss.[8] Infection of other organs can cause a wide range of symptoms.[9]\\n\" +\n" +
                "                        \"\\n\" +\n" +
                "                        \"Tuberculosis is spread through the air when people who have active TB in their lungs cough, spit, speak, or sneeze.\" +\n" +
                "                        \"[1][10] People with latent TB do not spread the disease.[1] Active infection occurs more often in people with HIV/AIDS and in those who smoke.\" +\n" +
                "                        \"[1] Diagnosis of active TB is based on chest X-rays, as well as microscopic examination and culture of body fluids.\" +\n" +
                "                        \"[11] Diagnosis of latent TB relies on the tuberculin skin test (TST) or blood tests.[11]"));
        data.add(new VaccinationBook("Tháng 10 Năm 2019", "Mũi\n1/1", "Tuberculosis", "28/10/2019", "1 tháng 27 ngày", R.drawable.ic_ellipse_202,"Newborn","Tuberculosis (TB) is an infectious disease usually caused by Mycobacterium tuberculosis (MTB) bacteria.\" +\n" +
                "                        \"[1] Tuberculosis generally affects the lungs, but can also affect other parts of the body.\" +\n" +
                "                        \"[1] Most infections do not have symptoms, in which case it is known as latent tuberculosis.\" +\n" +
                "                        \"[1] About 10% of latent infections progress to active disease which, if left untreated, kills about half of those affected.\" +\n" +
                "                        \"[1] The classic symptoms of active TB are a chronic cough with blood-containing mucus, fever, night sweats, and weight loss.\" +\n" +
                "                        \"[1] It was historically called \\\"consumption\\\" due to the weight loss.[8] Infection of other organs can cause a wide range of symptoms.[9]\\n\" +\n" +
                "                        \"\\n\" +\n" +
                "                        \"Tuberculosis is spread through the air when people who have active TB in their lungs cough, spit, speak, or sneeze.\" +\n" +
                "                        \"[1][10] People with latent TB do not spread the disease.[1] Active infection occurs more often in people with HIV/AIDS and in those who smoke.\" +\n" +
                "                        \"[1] Diagnosis of active TB is based on chest X-rays, as well as microscopic examination and culture of body fluids.\" +\n" +
                "                        \"[11] Diagnosis of latent TB relies on the tuberculin skin test (TST) or blood tests.[11]"));
        data.add(new VaccinationBook("Tháng 10 Năm 2019", "Mũi\n1/1", "Tuberculosis", "28/10/2019", "1 tháng 27 ngày", R.drawable.ic_ellipse_202,"Newborn","Tuberculosis (TB) is an infectious disease usually caused by Mycobacterium tuberculosis (MTB) bacteria.\" +\n" +
                "                        \"[1] Tuberculosis generally affects the lungs, but can also affect other parts of the body.\" +\n" +
                "                        \"[1] Most infections do not have symptoms, in which case it is known as latent tuberculosis.\" +\n" +
                "                        \"[1] About 10% of latent infections progress to active disease which, if left untreated, kills about half of those affected.\" +\n" +
                "                        \"[1] The classic symptoms of active TB are a chronic cough with blood-containing mucus, fever, night sweats, and weight loss.\" +\n" +
                "                        \"[1] It was historically called \\\"consumption\\\" due to the weight loss.[8] Infection of other organs can cause a wide range of symptoms.[9]\\n\" +\n" +
                "                        \"\\n\" +\n" +
                "                        \"Tuberculosis is spread through the air when people who have active TB in their lungs cough, spit, speak, or sneeze.\" +\n" +
                "                        \"[1][10] People with latent TB do not spread the disease.[1] Active infection occurs more often in people with HIV/AIDS and in those who smoke.\" +\n" +
                "                        \"[1] Diagnosis of active TB is based on chest X-rays, as well as microscopic examination and culture of body fluids.\" +\n" +
                "                        \"[11] Diagnosis of latent TB relies on the tuberculin skin test (TST) or blood tests.[11]"));
        adapter.setData(data);
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

