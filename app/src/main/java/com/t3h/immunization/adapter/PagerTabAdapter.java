package com.t3h.immunization.adapter;

import android.content.Context;
import android.icu.util.Calendar;
import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.t3h.immunization.fragment.AllFragment;
import com.t3h.immunization.fragment.InjectedFragment;
import com.t3h.immunization.fragment.MissFragment;
import com.t3h.immunization.fragment.NotinjectedFragment;
import com.t3h.immunization.model.GetBaby;
import com.t3h.immunization.model.InjectionGroup;
import com.t3h.immunization.model.Injections;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class PagerTabAdapter extends FragmentStatePagerAdapter {
    private List<String> section=new ArrayList<>();
    private List<List<Injections>> dataInjection =new ArrayList<>() ;
    private List<InjectionGroup> datagroup;
    private List<Injections> datainjection;
    private Context context;

    @RequiresApi(api = Build.VERSION_CODES.N)
    public PagerTabAdapter(@NonNull FragmentManager fm, List<Injections> data, List<InjectionGroup>arr) {
        super(fm);
        this.datagroup=arr;
        this.datainjection=data;
        calculatorSection(data);
        notifyDataSetChanged();
    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new AllFragment(groupDataInjection(datainjection,section),datagroup);


            case 1:
                return new InjectedFragment(groupDataInjected(datainjection,section),datagroup);

            case 2:
                return new NotinjectedFragment((groupDataInjectionPrepare(datainjection, section)),datagroup);


            case 3:
                return new MissFragment((groupDataInjectionMiss(datainjection, section)),datagroup);


            default:
                 return new AllFragment(groupDataInjection(datainjection,section),datagroup);

        }
    }
    @Override
    public int getCount() {
        return 4;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Tất Cả";
            case 1:
                return "ĐãTiêm";
            case 2:
                return "Chưa Tiêm";
            case 3:
                return "Bỏ Lỡ";

        }
        return getPageTitle(position);

    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    public List groupDataInjection(List<Injections> data, List<String> sections){

        for (String section: sections) {
            ArrayList<Injections> arrTemp = new ArrayList<>();
            for (Injections injections: data ) {
                if(injections.getIsInjected().equalsIgnoreCase("0")) {
                    long temp = (getMilliFromDate(GetBaby.getInstance().getBirthday()) +
                            ( Long.parseLong(String.valueOf(Long.parseLong(injections.getDate()) *
                                    Long.parseLong("" + (24 * 60 * 60 * 1000))))));
                    String dateInjection = getDate(temp, "MM/yyyy");

                    if(dateInjection.equalsIgnoreCase(section)){
                        arrTemp.add(injections);
                    }
                } else {
                    String date_injected = injections.getInjectedDate();
                    long temp = (getMilliFromDate(date_injected ));
                    String dateInjected = getDate(temp, "MM/yyyy");
                    if(dateInjected.equalsIgnoreCase(section)){
                        arrTemp.add(injections);
                    }
                }
            }
            if (arrTemp.size() > 0) {
                dataInjection.add(arrTemp);
            }
        }
        return dataInjection;
    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    public List groupDataInjected(List<Injections> data, List<String> sections){

        dataInjection = new ArrayList<>();
        for (String section: sections) {
            ArrayList<Injections> arrTemp = new ArrayList<>();
            for (Injections injections: data ) {
                if(injections.getIsInjected().equalsIgnoreCase("1")) {
                    String date_injected = injections.getInjectedDate();
                    long temp = (getMilliFromDate(date_injected ));
                    String dateInjected = getDate(temp, "MM/yyyy");
                    if(dateInjected.equalsIgnoreCase(section)){
                        arrTemp.add(injections);
                    }
                }
            }
            if (arrTemp.size() > 0) {
                dataInjection.add(arrTemp);
            }
        }
        return dataInjection;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public List groupDataInjectionMiss(List<Injections> data, List<String> sections){

        dataInjection = new ArrayList<>();
        for (String section: sections) {
            ArrayList<Injections> arrTemp = new ArrayList<>();
            for (Injections injections: data ) {
                if(injections.getIsInjected().equalsIgnoreCase("0")) {
                    long temp = (getMilliFromDate(GetBaby.getInstance().getBirthday()) +
                            ( Long.parseLong(String.valueOf(Long.parseLong(injections.getDate()) *
                                    Long.parseLong("" + (24 * 60 * 60 * 1000))))));
                    long tempCurrent = System.currentTimeMillis();
                    if (temp < tempCurrent) {
                        String dateInjection = getDate(temp, "MM/yyyy");

                        if(dateInjection.equalsIgnoreCase(section)){
                            arrTemp.add(injections);
                        }
                    }

                }
            }
            if (arrTemp.size() > 0) {
                dataInjection.add(arrTemp);
            }
        }
        return dataInjection;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public List groupDataInjectionPrepare(List<Injections> data, List<String> sections){

        dataInjection = new ArrayList<>();
        for (String section: sections) {
            ArrayList<Injections> arrTemp = new ArrayList<>();
            for (Injections injections: data ) {
                if(injections.getIsInjected().equalsIgnoreCase("0")) {
                    long temp = (getMilliFromDate(GetBaby.getInstance().getBirthday()) +
                             Long.parseLong(String.valueOf(Long.parseLong(injections.getDate()) *
                                     (Long.parseLong("" + (24 * 60 * 60 * 1000))))));
                    long tempCurrent = System.currentTimeMillis();
                    if (temp >= tempCurrent) {
                        Log.e("temp", "groupDataInjectionPrepare: "+temp );
                        Log.e("current", "groupDataInjectionPrepare: "+System.currentTimeMillis() );
                        String dateInjection = getDate(temp, "MM/yyyy");

                        if(dateInjection.equalsIgnoreCase(section)){
                            arrTemp.add(injections);
                        }
                    }

                }
            }
            if (arrTemp.size() > 0) {
                dataInjection.add(arrTemp);
            }
        }
        return dataInjection;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void calculatorSection(List<Injections> data){
        for(Injections injections: data){
            if (injections.getIsInjected().equalsIgnoreCase("0")){

                long temp = (getMilliFromDate(GetBaby.getInstance().getBirthday()) +
                         Long.parseLong(String.valueOf(Long.parseLong(injections.getDate()) *
                                 (Long.parseLong("" + (24 * 60 * 60 * 1000))))));
                String dateInjection = getDate(temp, "MM/yyyy");

                section.add(dateInjection);
            } else {
                String date_injected = injections.getInjectedDate();
                long temp = (getMilliFromDate(date_injected ));
                String dateInjected = getDate(temp, "MM/yyyy");
                section.add(dateInjected);
            }
        }
        ArrayList<String> arrTemp = new ArrayList<>();
        for (int i = 0; i < section.size(); i++) {
            if (!arrTemp.contains(section.get(i))) {
                arrTemp.add(section.get(i));
            }
        }
        Collections.sort(arrTemp, new Comparator<String>() {
            @Override
            public int compare(String s, String t1) {
                return ( getMilliFromDate("01/" + s ) > (getMilliFromDate("01/" + t1 )) ) ? 1 : -1;
            }
        });

        for (int i = 0; i < arrTemp.size(); i++) {
            Log.e("arrrrrr", "calculatorSection: "+ arrTemp.get(i) );
        }


        section = arrTemp;

        Log.e("arrrrrr", "calculatorSection: "+ arrTemp.size());
    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    public String getDate(long milliSeconds, String dateFormat) {
        SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliSeconds);
        return formatter.format(calendar.getTime());
    }
    public long getMilliFromDate(String dateFormat) {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        try {
            date = formatter.parse(dateFormat);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println("Today is " + date);
        return date.getTime();

    }
}

