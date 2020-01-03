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
import com.t3h.immunization.adapter.VaccineAdapter;
import com.t3h.immunization.model.Vaccine;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class VaccineFragment extends Fragment implements VaccineAdapter.ItemClickListener {
    private ArrayList<Vaccine> data;
    private VaccineAdapter adapter;
    @BindView(R.id.lv_vaccine)
    RecyclerView recyclerView;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.vaccine_fragment, container, false);
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
        data.add(new Vaccine(R.drawable.group407, "Tuberculosis", "Newborn",
                "Tuberculosis (TB) is an infectious disease usually caused by Mycobacterium tuberculosis (MTB) bacteria." +
                        "[1] Tuberculosis generally affects the lungs, but can also affect other parts of the body." +
                        "[1] Most infections do not have symptoms, in which case it is known as latent tuberculosis." +
                        "[1] About 10% of latent infections progress to active disease which, if left untreated, kills about half of those affected." +
                        "[1] The classic symptoms of active TB are a chronic cough with blood-containing mucus, fever, night sweats, and weight loss." +
                        "[1] It was historically called \"consumption\" due to the weight loss.[8] Infection of other organs can cause a wide range of symptoms.[9]\n" +
                        "\n" +
                        "Tuberculosis is spread through the air when people who have active TB in their lungs cough, spit, speak, or sneeze." +
                        "[1][10] People with latent TB do not spread the disease.[1] Active infection occurs more often in people with HIV/AIDS and in those who smoke." +
                        "[1] Diagnosis of active TB is based on chest X-rays, as well as microscopic examination and culture of body fluids." +
                        "[11] Diagnosis of latent TB relies on the tuberculin skin test (TST) or blood tests.[11]"));
        data.add(new Vaccine(R.drawable.group407, "Thủy Đậu", "Newborn",
                "Tuberculosis (TB) is an infectious disease usually caused by Mycobacterium tuberculosis (MTB) bacteria." +
                        "[1] Tuberculosis generally affects the lungs, but can also affect other parts of the body." +
                        "[1] Most infections do not have symptoms, in which case it is known as latent tuberculosis." +
                        "[1] About 10% of latent infections progress to active disease which, if left untreated, kills about half of those affected." +
                        "[1] The classic symptoms of active TB are a chronic cough with blood-containing mucus, fever, night sweats, and weight loss." +
                        "[1] It was historically called \"consumption\" due to the weight loss.[8] Infection of other organs can cause a wide range of symptoms.[9]\n" +
                        "\n" +
                        "Tuberculosis is spread through the air when people who have active TB in their lungs cough, spit, speak, or sneeze." +
                        "[1][10] People with latent TB do not spread the disease.[1] Active infection occurs more often in people with HIV/AIDS and in those who smoke." +
                        "[1] Diagnosis of active TB is based on chest X-rays, as well as microscopic examination and culture of body fluids." +
                        "[11] Diagnosis of latent TB relies on the tuberculin skin test (TST) or blood tests.[11]"));
        data.add(new Vaccine(R.drawable.group407, "Lao Phổi", "Newborn",
                "Tuberculosis (TB) is an infectious disease usually caused by Mycobacterium tuberculosis (MTB) bacteria." +
                        "[1] Tuberculosis generally affects the lungs, but can also affect other parts of the body." +
                        "[1] Most infections do not have symptoms, in which case it is known as latent tuberculosis." +
                        "[1] About 10% of latent infections progress to active disease which, if left untreated, kills about half of those affected." +
                        "[1] The classic symptoms of active TB are a chronic cough with blood-containing mucus, fever, night sweats, and weight loss." +
                        "[1] It was historically called \"consumption\" due to the weight loss.[8] Infection of other organs can cause a wide range of symptoms.[9]\n" +
                        "\n" +
                        "Tuberculosis is spread through the air when people who have active TB in their lungs cough, spit, speak, or sneeze." +
                        "[1][10] People with latent TB do not spread the disease.[1] Active infection occurs more often in people with HIV/AIDS and in those who smoke." +
                        "[1] Diagnosis of active TB is based on chest X-rays, as well as microscopic examination and culture of body fluids." +
                        "[11] Diagnosis of latent TB relies on the tuberculin skin test (TST) or blood tests.[11]"));
        data.add(new Vaccine(R.drawable.group407, "Cảm Cúm", "Newborn",
                "Tuberculosis (TB) is an infectious disease usually caused by Mycobacterium tuberculosis (MTB) bacteria." +
                        "[1] Tuberculosis generally affects the lungs, but can also affect other parts of the body." +
                        "[1] Most infections do not have symptoms, in which case it is known as latent tuberculosis." +
                        "[1] About 10% of latent infections progress to active disease which, if left untreated, kills about half of those affected." +
                        "[1] The classic symptoms of active TB are a chronic cough with blood-containing mucus, fever, night sweats, and weight loss." +
                        "[1] It was historically called \"consumption\" due to the weight loss.[8] Infection of other organs can cause a wide range of symptoms.[9]\n" +
                        "\n" +
                        "Tuberculosis is spread through the air when people who have active TB in their lungs cough, spit, speak, or sneeze." +
                        "[1][10] People with latent TB do not spread the disease.[1] Active infection occurs more often in people with HIV/AIDS and in those who smoke." +
                        "[1] Diagnosis of active TB is based on chest X-rays, as well as microscopic examination and culture of body fluids." +
                        "[11] Diagnosis of latent TB relies on the tuberculin skin test (TST) or blood tests.[11]"));
        data.add(new Vaccine(R.drawable.group407,"Viêm Não","Newborn",
                "Tuberculosis (TB) is an infectious disease usually caused by Mycobacterium tuberculosis (MTB) bacteria." +
                        "[1] Tuberculosis generally affects the lungs, but can also affect other parts of the body." +
                        "[1] Most infections do not have symptoms, in which case it is known as latent tuberculosis." +
                        "[1] About 10% of latent infections progress to active disease which, if left untreated, kills about half of those affected." +
                        "[1] The classic symptoms of active TB are a chronic cough with blood-containing mucus, fever, night sweats, and weight loss." +
                        "[1] It was historically called \"consumption\" due to the weight loss.[8] Infection of other organs can cause a wide range of symptoms.[9]\n" +
                        "\n" +
                        "Tuberculosis is spread through the air when people who have active TB in their lungs cough, spit, speak, or sneeze." +
                        "[1][10] People with latent TB do not spread the disease.[1] Active infection occurs more often in people with HIV/AIDS and in those who smoke." +
                        "[1] Diagnosis of active TB is based on chest X-rays, as well as microscopic examination and culture of body fluids." +
                        "[11] Diagnosis of latent TB relies on the tuberculin skin test (TST) or blood tests.[11]"));
        data.add(new Vaccine(R.drawable.group407,"Viêm Dạ Dày","Newborn",
                "Tuberculosis (TB) is an infectious disease usually caused by Mycobacterium tuberculosis (MTB) bacteria." +
                        "[1] Tuberculosis generally affects the lungs, but can also affect other parts of the body." +
                        "[1] Most infections do not have symptoms, in which case it is known as latent tuberculosis." +
                        "[1] About 10% of latent infections progress to active disease which, if left untreated, kills about half of those affected." +
                        "[1] The classic symptoms of active TB are a chronic cough with blood-containing mucus, fever, night sweats, and weight loss." +
                        "[1] It was historically called \"consumption\" due to the weight loss.[8] Infection of other organs can cause a wide range of symptoms.[9]\n" +
                        "\n" +
                        "Tuberculosis is spread through the air when people who have active TB in their lungs cough, spit, speak, or sneeze." +
                        "[1][10] People with latent TB do not spread the disease.[1] Active infection occurs more often in people with HIV/AIDS and in those who smoke." +
                        "[1] Diagnosis of active TB is based on chest X-rays, as well as microscopic examination and culture of body fluids." +
                        "[11] Diagnosis of latent TB relies on the tuberculin skin test (TST) or blood tests.[11]"));
        data.add(new Vaccine(R.drawable.group407,"Viêm Gan A","Newborn",
                "Tuberculosis (TB) is an infectious disease usually caused by Mycobacterium tuberculosis (MTB) bacteria." +
                        "[1] Tuberculosis generally affects the lungs, but can also affect other parts of the body." +
                        "[1] Most infections do not have symptoms, in which case it is known as latent tuberculosis." +
                        "[1] About 10% of latent infections progress to active disease which, if left untreated, kills about half of those affected." +
                        "[1] The classic symptoms of active TB are a chronic cough with blood-containing mucus, fever, night sweats, and weight loss." +
                        "[1] It was historically called \"consumption\" due to the weight loss.[8] Infection of other organs can cause a wide range of symptoms.[9]\n" +
                        "\n" +
                        "Tuberculosis is spread through the air when people who have active TB in their lungs cough, spit, speak, or sneeze." +
                        "[1][10] People with latent TB do not spread the disease.[1] Active infection occurs more often in people with HIV/AIDS and in those who smoke." +
                        "[1] Diagnosis of active TB is based on chest X-rays, as well as microscopic examination and culture of body fluids." +
                        "[11] Diagnosis of latent TB relies on the tuberculin skin test (TST) or blood tests.[11]"));
        data.add(new Vaccine(R.drawable.group407,"Viêm Gan B","Newborn",
                "Tuberculosis (TB) is an infectious disease usually caused by Mycobacterium tuberculosis (MTB) bacteria." +
                        "[1] Tuberculosis generally affects the lungs, but can also affect other parts of the body." +
                        "[1] Most infections do not have symptoms, in which case it is known as latent tuberculosis." +
                        "[1] About 10% of latent infections progress to active disease which, if left untreated, kills about half of those affected." +
                        "[1] The classic symptoms of active TB are a chronic cough with blood-containing mucus, fever, night sweats, and weight loss." +
                        "[1] It was historically called \"consumption\" due to the weight loss.[8] Infection of other organs can cause a wide range of symptoms.[9]\n" +
                        "\n" +
                        "Tuberculosis is spread through the air when people who have active TB in their lungs cough, spit, speak, or sneeze." +
                        "[1][10] People with latent TB do not spread the disease.[1] Active infection occurs more often in people with HIV/AIDS and in those who smoke." +
                        "[1] Diagnosis of active TB is based on chest X-rays, as well as microscopic examination and culture of body fluids." +
                        "[11] Diagnosis of latent TB relies on the tuberculin skin test (TST) or blood tests.[11]"));
        adapter.setData(data);

    }

    private void initView() {
        adapter = new VaccineAdapter(getContext());
        recyclerView.setAdapter(adapter);
        adapter.setOnListener(this);

    }

    @Override
    public void onClicked(int position) {
        Intent intent=new Intent(getContext(), DetailActivity.class);
        intent.putExtra("data",data.get(position));
        startActivity(intent);

    }

    @Override
    public void onLongClicked(int position) {

    }
}

