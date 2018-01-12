package xsk.com.wtuan.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

import xsk.com.wtuan.R;
import xsk.com.wtuan.bean.tuan.Tuan;

/**
 * Created by liyulong on 2018/1/10.
 */

public class PMBMyAdapter extends BaseAdapter {

    Context context;
    protected List<Tuan> tuans;

    public PMBMyAdapter(Context context) {
        this.context = context;
        tuans = new ArrayList<>();
    }

    public void addTuans(List<Tuan> tuans) {
        if (this.tuans == null) {
            this.tuans = new ArrayList<>();
        }
        this.tuans.addAll(tuans);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return tuans.size();
    }

    @Override
    public Object getItem(int i) {
        return tuans.get(i);
    }

    @Override
    public long getItemId(int i) {
        return tuans.get(i).hashCode();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View rootView = LayoutInflater.from(context).inflate(R.layout.adapter_pmb_my, viewGroup, false);


        return rootView;
    }
}
