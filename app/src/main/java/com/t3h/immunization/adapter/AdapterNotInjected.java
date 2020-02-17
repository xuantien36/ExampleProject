package com.t3h.immunization.adapter;

import android.content.Context;
import android.icu.util.Calendar;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import com.t3h.immunization.R;
import com.t3h.immunization.baby.model.GetBaby;
import com.t3h.immunization.statiscal.model.Injections;
import com.t3h.immunization.vacxin.model.InjectionGroup;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.BindView;

public class AdapterNotInjected extends BaseExpandableListAdapter {
    Context context;
    private List<List<Injections>> dataList;
    private List<InjectionGroup> groups;
   private  long temp;


    public AdapterNotInjected(Context context) {
        this.context = context;

    }

    public void setDataList(List<List<Injections>> dataList,List<InjectionGroup>groups) {
        this.dataList = dataList;
        this.groups=groups;
         notifyDataSetChanged();
    }

    @Override
    public int getGroupCount() {
        return dataList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this.dataList.get(groupPosition).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return null;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return null;
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
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        GroupHolder groupHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_lv_date_muitiem, parent, false);
            groupHolder = new GroupHolder();
             groupHolder.text = convertView.findViewById(R.id.tv_date);
            convertView.setTag(groupHolder);
        } else {
            groupHolder = (GroupHolder) convertView.getTag();
        }
         if (dataList.get(groupPosition).get(0).getDate()!=null){
             temp = (getMilliFromDate(GetBaby.getInstance().getBirthday()) +
                     (Long.parseLong(String.valueOf(Long.parseLong(dataList.get(groupPosition).get(0).getDate()) *
                             (Long.parseLong("" + (24 * 60 * 60 * 1000)))))));

             String dateInjection = getDate(temp, "MM");
             String year= getDate(temp, "yyyy");
             groupHolder.text.setText("Tháng  "+dateInjection+" Năm  "+ year);

         }
        return convertView;

    }
    class ChildHolder {
        @BindView(R.id.txtMui)
        TextView tvMui;
        @BindView(R.id.txtTenMuiTiem)
        TextView tvName;
        @BindView(R.id.txtNgayTiem)
        TextView tvngayTiem;
        @BindView(R.id.txtNgayConLai)
        TextView tvNgayConLai;
        @BindView(R.id.imgTrangThai)
        ImageView imtrangThai;
    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildHolder childHolder;

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_lv_muitiem, parent, false);
            childHolder = new ChildHolder();
            convertView.setTag(childHolder);
            childHolder.tvMui = convertView.findViewById(R.id.txtMui);
            childHolder.tvName = convertView.findViewById(R.id.txtTenMuiTiem);
            childHolder.tvngayTiem = convertView.findViewById(R.id.txtNgayTiem);
            childHolder.tvNgayConLai = convertView.findViewById(R.id.txtNgayConLai);
            childHolder.imtrangThai = convertView.findViewById(R.id.imgTrangThai);
        } else {
            childHolder = (ChildHolder) convertView.getTag();
        }
        long time = (getMilliFromDate(GetBaby.getInstance().getBirthday()) +
                (Long.parseLong(String.valueOf(Long.parseLong(dataList.get(groupPosition).get(childPosition).getDate()) *
                        Long.parseLong("" + (24 * 60 * 60 * 1000))))));

        if (System.currentTimeMillis()>=time){
            long days_left = (System.currentTimeMillis() - time);
            long days = ((days_left) / (1000 * 60 * 60 * 24));
//            String dateinjected = getDate(temp, "dd/MM/yyyy");
            childHolder.tvName.setText(groups.get(childPosition).getGroupTitle());
            childHolder.tvMui.setText("Mũi : " + (childPosition + 1) + "/" + getChildrenCount(groupPosition));
            if (dataList.get(groupPosition).get(childPosition).getIsInjected().equalsIgnoreCase("0") &&
                    (System.currentTimeMillis() >= ((getMilliFromDate(GetBaby.getInstance().getBirthday()) +
                            (Long.parseLong(String.valueOf(Long.parseLong(dataList.get(groupPosition).get(childPosition).getDate()) *
                                    Long.parseLong("" + (24 * 60 * 60 * 1000))))))))) {
                childHolder.imtrangThai.setImageResource(R.drawable.ic_ellipse2);
                childHolder.tvNgayConLai.setText("Bỏ lỡ : " + (days) + " ngày");

            } else if (dataList.get(groupPosition).get(childPosition).getIsInjected().equalsIgnoreCase("1")) {
                childHolder.imtrangThai.setImageResource(R.drawable.ic_ellipse_200);
                childHolder.tvNgayConLai.setText("Đã tiêm : " + days + " ngày");

            } else if (dataList.get(groupPosition).get(childPosition).getIsInjected().equalsIgnoreCase("0") &&
                    (System.currentTimeMillis() < ((getMilliFromDate(GetBaby.getInstance().getBirthday()) +
                            (Long.parseLong(String.valueOf(Long.parseLong(dataList.get(groupPosition).get(childPosition).getDate()) *
                                    Long.parseLong("" + (24 * 60 * 60 * 1000))))))))) {
                childHolder.imtrangThai.setImageResource(R.drawable.ic_ellipse_202);
                childHolder.tvNgayConLai.setText("Chưa tiêm : " + (days) + " ngày");
            }
            childHolder.tvngayTiem.setText("Ngày tiêm : " + getDate(time, "dd/MM/yyyy"));
        }else {
            long days_left = (temp - System.currentTimeMillis());
            long days =  ((days_left) / (1000 * 60 * 60 * 24));
//            String dateinjected = getDate(temp, "dd/MM/yyyy");
            childHolder.tvName.setText(groups.get(childPosition).getGroupTitle());
            childHolder.tvMui.setText("Mũi : " + (childPosition + 1) + "/" + getChildrenCount(groupPosition));
            if (dataList.get(groupPosition).get(childPosition).getIsInjected().equalsIgnoreCase("0") &&
                    (System.currentTimeMillis() >= ((getMilliFromDate(GetBaby.getInstance().getBirthday()) +
                            (Long.parseLong(String.valueOf(Long.parseLong(dataList.get(groupPosition).get(childPosition).getDate()) *
                                    Long.parseLong("" + (24 * 60 * 60 * 1000))))))))) {
                childHolder.imtrangThai.setImageResource(R.drawable.ic_ellipse2);
                childHolder.tvNgayConLai.setText("Bỏ lỡ : " + (days) + " ngày");

            } else if (dataList.get(groupPosition).get(childPosition).getIsInjected().equalsIgnoreCase("1")) {
                childHolder.imtrangThai.setImageResource(R.drawable.ic_ellipse_200);
                childHolder.tvNgayConLai.setText("Đã tiêm : " + days + " ngày");

            } else if (dataList.get(groupPosition).get(childPosition).getIsInjected().equalsIgnoreCase("0") &&
                    (System.currentTimeMillis() < ((getMilliFromDate(GetBaby.getInstance().getBirthday()) +
                            (Long.parseLong(String.valueOf(Long.parseLong(dataList.get(groupPosition).get(childPosition).getDate()) *
                                    Long.parseLong("" + (24 * 60 * 60 * 1000))))))))) {
                childHolder.imtrangThai.setImageResource(R.drawable.ic_ellipse_202);
                childHolder.tvNgayConLai.setText("Chưa tiêm : " + (days) + " ngày");
            }
            childHolder.tvngayTiem.setText("Ngày tiêm : " + getDate(time, "dd/MM/yyyy"));
        }
            return convertView;
        }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
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