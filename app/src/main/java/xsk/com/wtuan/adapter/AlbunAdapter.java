package xsk.com.wtuan.adapter;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import xsk.com.wtuan.R;
import xsk.com.wtuan.bean.album.Album;

/**
 * Created by liyulong on 2018/1/10.
 */

public class AlbunAdapter extends BaseAdapter {
    private Context context;
    private List<Album> albums = new ArrayList<>();

    public AlbunAdapter(Context context) {
        this.context = context;
    }

    public void clear() {
        albums.clear();
        notifyDataSetChanged();
    }
    public void add(List<Album> albums) {
        if (this.albums == null) {
            this.albums = new ArrayList<>();
        }
        this.albums.addAll(albums);
        ((Activity) context).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getCount() {
        return this.albums.size();
    }

    @Override
    public Object getItem(int i) {
        return this.albums.get(i);
    }

    @Override
    public long getItemId(int i) {
        return this.albums.get(i).hashCode();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View contentView = LayoutInflater.from(context).inflate(R.layout.adapter_album, null, false);
        ImageView coverV = contentView.findViewById(R.id.album_cover);
        TextView nameV = contentView.findViewById(R.id.album_name);
        Album album = albums.get(i);

        try {
            Picasso.with(context)
                    .load(Uri.parse(album.cover))
                    .placeholder(R.drawable.ic_empty_zhihu)
                    .into(coverV);

            nameV.setText(album.name);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return contentView;
    }
}
