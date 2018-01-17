package xsk.com.wtuan.adapter;

import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by liyulong on 2018/1/17.
 */

public abstract class GeneralAdapter<T> extends BaseAdapter {
    List<T> data;
    public GeneralAdapter(List<T> data) {
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return data.get(i).hashCode();
    }


}
