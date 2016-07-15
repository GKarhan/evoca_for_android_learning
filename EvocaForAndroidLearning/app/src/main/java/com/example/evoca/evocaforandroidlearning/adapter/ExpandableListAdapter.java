package com.example.evoca.evocaforandroidlearning.adapter;

/**
 * Created by Evoca-PC on 7/2/2016.
 */
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.evoca.evocaforandroidlearning.Model.Child;
import com.example.evoca.evocaforandroidlearning.Model.Group;
import com.example.evoca.evocaforandroidlearning.R;
import com.example.evoca.evocaforandroidlearning.activity.ListActivity;

import java.util.ArrayList;

/**
 * this is expandable list adapter
 */
public class ExpandableListAdapter extends BaseExpandableListAdapter {
    LayoutInflater inflater;

    /*list of group */
    private ArrayList<Group> groups;
    public ExpandableListAdapter(Context context, ArrayList<Group> groups) {
        //super();
        this.groups=groups;
        inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    //Child

    /*public void addItem(Child child,Group group) {
        if(!groups.contains(group)) {
            groups.add(group);
        }
        int index=groups.indexOf(group);
        ArrayList<Child> ch=groups.get(index).getChildrens();

        ch.add(child);
        groups.get(index).setChildrens(ch);
    }*/

    public Child getChild(int groupPosition, int childPosition) {
        /*ArrayList<Child> ch=groups.get(groupPosition).getChildrens();
        return ch.get(childPosition);*/
        return groups.get(groupPosition).getCourses().get(childPosition);
    }
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        if (groups.get(groupPosition).getCourses()!= null) {
            /*ArrayList<Child> ch = groups.get(groupPosition).getChildrens();
            return ch.size();*/
            return groups.get(groupPosition).getCourses().size();
        }
        return 0;
    }
    public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild,
                             View convertView, ViewGroup parent) {

       final Child  currentChild = getChild(groupPosition,childPosition);

        if(convertView==null) {
            convertView=inflater.inflate(R.layout.child_view, parent,false);
        }
        TextView childName = (TextView) convertView.findViewById(R.id.textViewChildName);
        childName.setText(currentChild.getTitle());
        currentChild.getText();
        // kam el childName.setText(currentChild.getText());
        return convertView;
    }

    //Group

    public Group getGroup(int groupPosition) {
        return groups.get(groupPosition);
    }

    public int getGroupCount() {
        return groups.size();
    }

    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    public View getGroupView(final int groupPosition, boolean isExpanded, View convertView,
                             ViewGroup parent) {

        Group currentGroup = getGroup(groupPosition);
        if(convertView==null) {
            convertView=inflater.inflate(R.layout.group_view, parent,false);
        }
        TextView groupName = (TextView) convertView.findViewById(R.id.textViewGroupName);
        groupName.setText(currentGroup.getMain_title());

        return convertView;
    }

    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    public boolean hasStableIds() {
        return true;
    }


}
