package com.t3h.immunization.adapter;

import android.content.Context;
import android.icu.util.Calendar;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import com.t3h.immunization.R;
import com.t3h.immunization.model.GetBaby;
import com.t3h.immunization.model.InjectionGroup;
import com.t3h.immunization.model.Injections;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class ExpandableListAdapter extends BaseExpandableListAdapter {
    Context context;
    List<InjectionGroup> groupList;
    HashMap<String, List<Injections>> list_item = new HashMap<>();
    List<Injections> tempList;
    long setDate;
    private callBackChild child;

    public void setChildListener(callBackChild child) {
        this.child = child;
    }

    public ExpandableListAdapter(Context context, List<InjectionGroup> groupList) {
        this.context = context;
        this.groupList = groupList;
    }

    @Override
    public int getGroupCount() {
        return list_item.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        Log.e("CHIEEE", "getChildrenCount: " + this.list_item.get(groupList.get(groupPosition).getGroupTitle()).size());
        return this.list_item.get(groupList.get(groupPosition).getGroupTitle()).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return groupList.get(groupPosition);
    }

    @Override
    public Injections getChild(int groupPosition, int childPosition) {
        return list_item.get(groupList.get(groupPosition).getGroupTitle()).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    class GroupHolder {
        TextView text;
        ImageView image;
        TextView tvInjected, tvNotInjected, tvMiss;

    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        GroupHolder groupHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_statistical, parent, false);
            groupHolder = new GroupHolder();
            groupHolder.text = (TextView) convertView.findViewById(R.id.name_statistical);
            groupHolder.image = (ImageView) convertView.findViewById(R.id.icon);
            groupHolder.tvInjected = convertView.findViewById(R.id.tv_injected);
            groupHolder.tvNotInjected = convertView.findViewById(R.id.tv_not_injected);
            groupHolder.tvMiss = convertView.findViewById(R.id.tv_miss);
            convertView.setTag(groupHolder);
        } else {
            groupHolder = (GroupHolder) convertView.getTag();
        }
        groupHolder.text.setText(groupList.get(groupPosition).getGroupTitle());
        groupHolder.tvInjected.setText(countInjected(list_item.get(groupList.get(groupPosition).getGroupTitle())));
        groupHolder.tvNotInjected.setText(countNotInjected(list_item.get(groupList.get(groupPosition).getGroupTitle())));
        groupHolder.tvMiss.setText(countMisInjected(list_item.get(groupList.get(groupPosition).getGroupTitle())));

        if (isExpanded) {
            groupHolder.image.setImageResource(R.drawable.ic_path_3678);
        } else {
            groupHolder.image.setImageResource(R.drawable.ic_path_3679);
        }
        return convertView;

    }
    class ChildHolder {
        TextView name;
        TextView date;
        ImageView image;
    }
    public String countInjected(List<Injections> injectionsList) {
        int count = 0;
        for (Injections injections : injectionsList) {
            if (injections.getIsInjected().equalsIgnoreCase("1")) {
                count++;
            }

        }
        return String.valueOf(count);
    }

    public String countNotInjected(List<Injections> injectionsList) {
        int count = 0;
        for (Injections injections : injectionsList) {
            if (injections.getIsInjected().equalsIgnoreCase("0") &&  (System.currentTimeMillis() < ((getMilliFromDate(GetBaby.getInstance().getBirthday()) +
                    ( Long.parseLong(String.valueOf(Long.parseLong(injections.getDate()) * Long.parseLong("" + (24 * 60 * 60 * 1000))))))))) {
                count++;

            }
        }
        return String.valueOf(count);
    }
    public String countMisInjected(List<Injections> injectionsList) {
        int count = 0;
        for (Injections injections : injectionsList) {
            if (injections.getIsInjected().equalsIgnoreCase("0") &&
                    (System.currentTimeMillis() >= ((getMilliFromDate(GetBaby.getInstance().getBirthday()) +
                            ( Long.parseLong(String.valueOf(Long.parseLong(injections.getDate()) * Long.parseLong("" + (24 * 60 * 60 * 1000))))))))) {
                count++;

            }
        }
        return String.valueOf(count);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildHolder childHolder;

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.child_list, parent, false);
            childHolder = new ChildHolder();
            childHolder.name = convertView.findViewById(R.id.name);
            childHolder.date = convertView.findViewById(R.id.date);
            childHolder.image = convertView.findViewById(R.id.image);

            convertView.setTag(childHolder);
        } else {
            childHolder = (ChildHolder) convertView.getTag();

        }
        childHolder.name.setText("Mũi : " + (childPosition + 1) + "/" + getChildrenCount(groupPosition));

        setDate = Long.parseLong(String.valueOf(Long.parseLong(getChild(groupPosition, childPosition).getDate()) *
                Long.parseLong("" + (24 * 60 * 60 * 1000))));
        Log.e("setdate", "getChildView: " + childPosition + "\\\\\\\\\\" + setDate);

        childHolder.date.setText(getDate(getMilliFromDate(GetBaby.getInstance().getBirthday()) + setDate, "dd/MM/yyyy"));
        Log.e("BBBBBBB", "getChildView: " + childPosition + "\\\\\\\\\\" + getChild(groupPosition, childPosition).getDate());
        Log.e("tongdate", "getChildView: " + childPosition + "\\\\\\\\\\" + getMilliFromDate(GetBaby.getInstance().getBirthday()));

        Log.e("TAG", "realTime 1 : "+System.currentTimeMillis() );
        Log.e("TAG", "realTime 2: "+(getMilliFromDate(GetBaby.getInstance().getBirthday()) + setDate) );

            if (getChild(groupPosition,childPosition).getIsInjected().equalsIgnoreCase("0") &&
                    (System.currentTimeMillis() >= ((getMilliFromDate(GetBaby.getInstance().getBirthday()) +
                            ( Long.parseLong(String.valueOf(Long.parseLong(getChild(groupPosition,childPosition).getDate()) *
                                    Long.parseLong("" + (24 * 60 * 60 * 1000))))))))) {
                childHolder.image.setImageResource(R.drawable.ic_ellipse2);
                childHolder.date.setBackgroundResource(R.drawable.shape_custom_miss_injected);
            }else if (getChild(groupPosition,childPosition).getIsInjected().equalsIgnoreCase("1")){
                childHolder.date.setBackgroundResource(R.drawable.shape_custom_injectd);
                childHolder.image.setImageResource(R.drawable.ic_ellipse_200);

            }else if (getChild(groupPosition,childPosition).getIsInjected().equalsIgnoreCase("0") &&
                    (System.currentTimeMillis() < ((getMilliFromDate(GetBaby.getInstance().getBirthday()) +
                            ( Long.parseLong(String.valueOf(Long.parseLong(getChild(groupPosition,childPosition).getDate()) *
                                    Long.parseLong("" + (24 * 60 * 60 * 1000))))))))){
                childHolder.image.setImageResource(R.drawable.ic_ellipse_202);
                childHolder.date.setBackgroundResource(R.drawable.shape_custom_not_injectd);

            }
        if (child != null) {
            convertView.setOnClickListener(view -> child.onclickChild(childPosition, childHolder.date.getText().toString(),
                    groupList.get(groupPosition).getGroupTitle()));
        }
        return convertView;
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

    @RequiresApi(api = Build.VERSION_CODES.N)
    public String getDate(long milliSeconds, String dateFormat) {
        SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliSeconds);
        return formatter.format(calendar.getTime());
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    public void setDataStatis(List<InjectionGroup> injectionGroupList, List<Injections> dataList) {
        for (InjectionGroup group : injectionGroupList) {
            tempList = new ArrayList<>();
            for (Injections injections : dataList) {
                if (group.getGroupInjection().equals(injections.getGroupInjection())) {
                    tempList.add(injections);
                }
            }

            Collections.sort(tempList, new Comparator<Injections>() {
                @Override
                public int compare(Injections o1, Injections o2) {
                    if (Integer.parseInt(o1.getDate()) > Integer.parseInt(o2.getDate())) {
                        return 1;
                    } else
                        return -1;
                }
            });
            for (Injections i : tempList) {
                Log.e("AAAA", "setDataStatis:   " + i.getDate());
            }
            Log.e("AAAA", "----------------------------  ");
            list_item.put(group.getGroupTitle(), tempList);

        }
        notifyDataSetChanged();
    }

    public interface callBackChild {
        void onclickChild(int position, String date, String name);
    }
}