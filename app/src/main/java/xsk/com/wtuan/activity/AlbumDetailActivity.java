package xsk.com.wtuan.activity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import xsk.com.wtuan.R;
import xsk.com.wtuan.bean.RequestResultBean;
import xsk.com.wtuan.net.JsonResultRequest;
import xsk.com.wtuan.net.request.album.AlbumCoverChangeRequest;

/**
 * 查看相册内容
 */
public class AlbumDetailActivity extends AppCompatActivity {
    ViewPager albumViewPager;
    AlbumStateAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album_detail);
        albumViewPager = findViewById(R.id.album_deail_viewpager);
        adapter = new AlbumStateAdapter(getSupportFragmentManager());
        albumViewPager.setAdapter(adapter);
        albumViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                adapter.change(null, position);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        albumViewPager.setCurrentItem(0, true);
    }

    class AlbumStateAdapter extends FragmentStatePagerAdapter {
        List<AlbumInnerFragment> items;

        public void change(Object model, int position) {
            AlbumInnerFragment innerFragment = (AlbumInnerFragment) this.getItem(position);
            innerFragment.changeAlbumPic(model, position);

        }

        public AlbumStateAdapter(FragmentManager fm) {
            super(fm);
            items = new ArrayList<>();
        }

        @Override
        public Fragment getItem(int position) {
            if (items == null) {
                items = new ArrayList<>();

            }
            Fragment tmp;
            if (position < items.size()) {
            } else {
                items.add(new AlbumInnerFragment());
            }
            tmp = items.get(position);


            return items.get(position);
        }

        @Override
        public int getCount() {
            return 10;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}

@SuppressLint("ValidFragment")
class AlbumInnerFragment extends Fragment {
    ImageView picView;
    TextView indicator;

    void changeAlbumPic(Object model, int position) {
        Picasso.with(getContext())
                .load(R.drawable.arrow_right)
                .placeholder(R.drawable.ic_empty_zhihu)
                .into(picView);
        indicator.setText("" + position);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frgment_inner_album_detail, null, false);
        picView = view.findViewById(R.id.album_img);
        indicator = view.findViewById(R.id.album_msg);

        view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());

                builder.setCancelable(true);
                builder.setTitle("提示")
                        .setMessage("选择相片用途")
                        .setNegativeButton("设为封面", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                //changeAlbumCover(null);

                            }
                        })
                .setPositiveButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                builder.create().show();
                return false;
            }
        });
        return view;
    }

    /**
     * 使用相册中的图片作为封面
     * @param paramters
     */
    protected void changeAlbumCover(Map<String, Object> paramters) {
        AlbumCoverChangeRequest request = new AlbumCoverChangeRequest();
        request.post(new HashMap<String, String>(), RequestResultBean.class, new JsonResultRequest.OnBeanResult() {
            @Override
            public void onSuccess(RequestResultBean bean) {

            }

            @Override
            public void onFailure(Exception e) {

            }
        });

    }
//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_center);
//    }
}

