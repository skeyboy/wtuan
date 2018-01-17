package xsk.com.wtuan.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import xsk.com.wtuan.R;

/**
 * Created by liyulong on 2018/1/17.
 */

public class EssenceFragmentAdapter extends GeneralAdapter {
    Context context;
    public EssenceFragmentAdapter(List data, Context context) {
        super(data);
        this.context = context;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View contentView = LayoutInflater.from(context).inflate(R.layout.fragment_essence, null, false);

        return contentView;
    }
}
