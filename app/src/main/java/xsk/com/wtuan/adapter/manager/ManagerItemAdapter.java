package xsk.com.wtuan.adapter.manager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import xsk.com.wtuan.R;

/**
 * Created by liyulong on 2018/1/12.
 */

public class ManagerItemAdapter extends BaseAdapter {
    Context context;

    public ManagerItemAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return 10;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View contentView = LayoutInflater.from(context).inflate(R.layout.adapter_manager_item,null,false);
        return contentView;
    }
}

