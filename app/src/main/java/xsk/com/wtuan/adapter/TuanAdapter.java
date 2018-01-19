package xsk.com.wtuan.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import xsk.com.wtuan.R;
import xsk.com.wtuan.bean.albumgroud.TuanGroup;
import xsk.com.wtuan.utils.Utils;

/**
 * Created by liyulong on 2018/1/19.
 */

public class TuanAdapter extends BaseAdapter {
    List<TuanGroup> albums = new ArrayList<>();
    Context context;

    public TuanAdapter(Context context) {
        this.context = context;
    }

    public void add(List<TuanGroup> albums) {
        this.albums.addAll(albums);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return albums.size();
    }

    @Override
    public Object getItem(int position) {
        return albums.get(position);
    }

    @Override
    public long getItemId(int position) {
        return albums.get(position).hashCode();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rootView = LayoutInflater.from(context).inflate(R.layout.adapter_pmb_gride, null, false);
        ImageView coverView = rootView.findViewById(R.id.image);
        TextView albumNameView = rootView.findViewById(R.id.name);

        TuanGroup albumGroup = albums.get(position);
        Utils.picasso(context,albumGroup.logo, coverView);

        albumNameView.setText(albumGroup.name);

        return rootView;
    }
}
