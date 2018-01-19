package xsk.com.wtuan.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import xsk.com.wtuan.R;
import xsk.com.wtuan.bean.tuan.Tuan;
import xsk.com.wtuan.utils.Utils;

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
        CircleImageView circleImageView = rootView.findViewById(R.id.tuan_cover);
        TextView nameV = rootView.findViewById(R.id.tuan_name);
        TextView infoV = rootView.findViewById(R.id.tuan_info);

        Tuan tuan = tuans.get(i);

        Picasso.with(context).load(Utils.Res_HOST+tuan.logo).placeholder(R.drawable.ic_empty_zhihu).into(circleImageView);
        nameV.setText(tuan.name);
        infoV.setText(tuan.description);
        return rootView;
    }
}
