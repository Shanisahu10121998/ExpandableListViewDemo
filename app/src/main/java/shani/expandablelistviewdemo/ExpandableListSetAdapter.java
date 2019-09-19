package shani.expandablelistviewdemo;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

public class ExpandableListSetAdapter extends BaseExpandableListAdapter {

    Context context;
    List<String> _listDataHeader;
    HashMap<String, List<String>> _listDtaChild;

    public ExpandableListSetAdapter(MainActivity mainActivity, List<String> listDataHeader, HashMap<String, List<String>> listDtaChild) {
    this.context=mainActivity;
    this._listDataHeader=listDataHeader;
    this._listDtaChild=listDtaChild;
    }

    @Override
    public int getGroupCount() {
        return this._listDataHeader.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this._listDtaChild.get(this._listDataHeader.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return  this._listDataHeader.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {

        return this._listDtaChild.get(this._listDataHeader.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return groupPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean b, View view, ViewGroup viewGroup) {

        final String headerTitle = (String) getGroup(groupPosition);
        if (view==null){
            LayoutInflater layoutInflater= (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view=layoutInflater.inflate(R.layout.list_group,null);

        }
        TextView lblListHeader = view.findViewById(R.id.lblListHeader);
        lblListHeader.setTypeface(null, Typeface.BOLD);
        lblListHeader.setText(headerTitle);
        return view;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean b, View view, ViewGroup viewGroup) {
         final String childText = (String) getChild(groupPosition,childPosition);
        if (view==null){
        LayoutInflater layoutInflater= (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view=layoutInflater.inflate(R.layout.list_item,null);

    }
    TextView txtListChild = view.findViewById(R.id.lblListItem);
        txtListChild.setText(childText);
        return view;
}

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
