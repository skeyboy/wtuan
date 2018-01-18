package xsk.com.wtuan.activity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.engine.impl.PicassoEngine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import xsk.com.wtuan.R;
import xsk.com.wtuan.bean.RequestResultBean;
import xsk.com.wtuan.bean.file.FileResultBean;
import xsk.com.wtuan.net.JsonResultRequest;
import xsk.com.wtuan.net.PMBRequest;
import xsk.com.wtuan.net.request.album.AlbumAddPicRequest;
import xsk.com.wtuan.net.request.album.AlbumCoverChangeRequest;
import xsk.com.wtuan.utils.Utils;

/**
 * 查看相册内容
 */
public class AlbumDetailActivity extends AppCompatActivity {
    ViewPager albumViewPager;
    AlbumStateAdapter adapter;
    private static final int REQUEST_CODE_CHOOSE = 23;//定义请求码常量

    public void uploadPhotos(View view) {
        Matisse.from(this)
                .choose(MimeType.allOf())//照片视频全部显示
                .countable(true)//有序选择图片
                .maxSelectable(9)//最大选择数量为9
                .gridExpectedSize(120)//图片显示表格的大小getResources()

//                .getDimensionPixelSize(R.dimen.grid_expected_size)
                .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)//图像选择和预览活动所需的方向。
                .thumbnailScale(0.85f)//缩放比例
                .theme(R.style.Matisse_Zhihu)//主题  暗色主题 R.style.Matisse_Dracula
                .imageEngine(new PicassoEngine())//加载方式
                .forResult(REQUEST_CODE_CHOOSE);//请求码
    }

    List<Uri> mSelected;

    @Override      //接收返回的地址
    protected void onActivityResult(final int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_CHOOSE && resultCode == RESULT_OK) {
            mSelected = Matisse.obtainResult(data);
            Log.d("Matisse", "mSelected: " + mSelected);
            for (final Uri uri : mSelected) {

                String[] proj = {MediaStore.Images.Media.DATA};
                Cursor actualimagecursor = this.managedQuery(uri, proj, null, null, null);
                int actual_image_column_index = actualimagecursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                actualimagecursor.moveToFirst();


                String img_path = actualimagecursor.getString(actual_image_column_index);


                Utils.fileUpload(img_path, new Utils.Upload() {
                    @Override
                    public void onSuccess(final FileResultBean response) {
                        Log.d(response.msg, response.toString());
                        Utils.runOnUiThread(AlbumDetailActivity.this, new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(AlbumDetailActivity.this, response.toString(), Toast.LENGTH_SHORT)
                                        .show();
                            }
                        });

                        int albumId = getIntent().getIntExtra("albumId", 0);

                        HashMap<String, String> paramaters = new HashMap<>();
                        paramaters.put("albumId", String.valueOf(albumId));
                        paramaters.put("api_token", PMBRequest.token);
                        paramaters.put("pics", Utils.join(",", response.data.path));

                        AlbumAddPicRequest request = new AlbumAddPicRequest();


                        request.post(paramaters, RequestResultBean.class, new JsonResultRequest.OnBeanResult() {
                            @Override
                            public void onSuccess(final RequestResultBean bean) {
                                Utils.runOnUiThread(AlbumDetailActivity.this, new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(AlbumDetailActivity.this, bean.toString(), Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }

                            @Override
                            public void onFailure(Exception e) {

                            }
                        });
                    }

                    @Override
                    public void onFailure() {

                    }
                });

            }
        }
    }

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
     *
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

