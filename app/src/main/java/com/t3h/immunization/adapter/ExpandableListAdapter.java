package com.t3h.immunization.adapter;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.t3h.immunization.R;
import com.t3h.immunization.model.InjectionGroup;
import com.t3h.immunization.model.Injections;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ExpandableListAdapter extends BaseExpandableListAdapter {
    Context context;
    List<InjectionGroup> groupList;
    HashMap<String, List<Injections>> list_item = new HashMap<>();

    public ExpandableListAdapter(Context context, List<InjectionGroup> groupList) {
        this.context = context;
        this.groupList = groupList;
    }

    @Override
    public  int getGroupCount() {
        return list_item.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        Log.e("CHIEEE", "getChildrenCount: "+this.list_item.get(groupList.get(groupPosition).getGroupTitle()).size() );
        return this.list_item.get(groupList.get(groupPosition).getGroupTitle()).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return groupList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return list_item.get(groupList.get(groupPosition)).get(childPosition);
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

    class GroupHolder{
        TextView text;
        ImageView image;

    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        GroupHolder groupHolder;
        if (convertView ==  null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_statistical, parent, false);
            groupHolder = new GroupHolder();
            groupHolder.text =(TextView) convertView.findViewById(R.id.name_statistical);
            groupHolder.image = (ImageView) convertView.findViewById(R.id.icon);
            convertView.setTag(groupHolder);
        }else {
            groupHolder = (GroupHolder) convertView.getTag();
        }


        groupHolder.text.setText(groupList.get(groupPosition).getGroupTitle());

        if(isExpanded) {
            groupHolder.image.setImageResource(R.drawable.ic_path_3678);
        }else {
            groupHolder.image.setImageResource(R.drawable.ic_path_3679);
        }

        return convertView;

    }
    class ChildHolder{
        TextView name;
        TextView date;
        ImageView image;

    }
    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
           ChildHolder childHolder;
        if (convertView ==  null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.child_list, parent, false);
            childHolder=new ChildHolder();
            childHolder.name= convertView.findViewById(R.id.name);
            childHolder.date=convertView.findViewById(R.id.date);
            childHolder.image=convertView.findViewById(R.id.image);
            convertView.setTag(childHolder);
        }else {
            childHolder= (ChildHolder) convertView.getTag();

        }
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    public void setDataStatis(List<InjectionGroup> injectionGroupList, List<Injections> dataList){
        for (InjectionGroup group: injectionGroupList) {
            List<Injections> tempList = new ArrayList<>();
            for (Injections injections: dataList) {
                if (group.getGroupInjection().equals(injections.getGroupInjection())){
                    tempList.add(injections);
                    Log.e("CHIEE", "setDataStatis: adddddddddd" );
                }
            }
            list_item.put(group.getGroupTitle(),tempList);

        }
        notifyDataSetChanged();
    }

}