package xsk.com.wtuan.adapter;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;

import java.util.List;

import xsk.com.wtuan.R;
import xsk.com.wtuan.bean.school.Area;


/**
 * Created by liyulong on 2018/1/8.
 */

public class SchoolViewAdapter implements ExpandableListAdapter {
    private List<Area> items;

    private Context context;
    public SchoolViewAdapter(Context context,List<Area> items) {
        this.items = items;
        this.context = context;
    }

    @Override
    public void registerDataSetObserver(DataSetObserver dataSetObserver) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {

    }

    @Override
    public int getGroupCount() {
        return items.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return items.get(i).schoolList.size();
    }

    @Override
    public Object getGroup(int i) {
        return items.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        return items.get(i).schoolList.get(i1);
    }

    @Override
    public long getGroupId(int i) {
        return items.get(i).hashCode();
    }

    @Override
    public long getChildId(int i, int i1) {
        return items.get(i).schoolList.get(i1).hashCode();
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
        View header = LayoutInflater.from(context)
                .inflate(R.layout.adapter_school_header, null, false);
        return header;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {

        View childView = LayoutInflater.from(context)
                .inflate(R.layout.adapter_school, null, false);

        return childView;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }

    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public void onGroupExpanded(int i) {

    }

    @Override
    public void onGroupCollapsed(int i) {

    }

    @Override
    public long getCombinedChildId(long l, long l1) {
        return 0;
    }

    @Override
    public long getCombinedGroupId(long l) {
        return 0;
    }
}
