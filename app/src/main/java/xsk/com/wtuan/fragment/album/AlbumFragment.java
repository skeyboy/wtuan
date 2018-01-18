package xsk.com.wtuan.fragment.album;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import okhttp3.Response;
import xsk.com.wtuan.R;
import xsk.com.wtuan.activity.AlbumDetailActivity;
import xsk.com.wtuan.adapter.AlbunAdapter;
import xsk.com.wtuan.bean.RequestResultBean;
import xsk.com.wtuan.bean.album.Album;
import xsk.com.wtuan.bean.album.AlbumResultBean;
import xsk.com.wtuan.net.JsonResultRequest;
import xsk.com.wtuan.net.request.album.AlbumCreateRequest;
import xsk.com.wtuan.net.request.album.AlbumRequest;
import xsk.com.wtuan.utils.Utils;

/**
 * A simple {@link Fragment} subclass.
 */
public class AlbumFragment extends Fragment {
    GridView albumGride;
    AlbunAdapter albunAdapter;

    EditText albumName;
    TextView albumCreat;
    Switch albumPrivate;


    public AlbumFragment() {
        // Required empty public constructor
    }

    private void toast(final String msg) {
        Activity activity = (Activity) getContext();
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
            }
        });

    }

    protected void initView(final View view) {
        albumGride = view.findViewById(R.id.album_grade);
        albumCreat = view.findViewById(R.id.album_create);
        albumName = view.findViewById(R.id.album_name);
        albumPrivate = view.findViewById(R.id.album_private);
        if (Utils.isEmpty(albumName)) {

        } else {
            albumCreat.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AlbumCreateRequest createRequest = new AlbumCreateRequest();
                    boolean isPrive = albumPrivate.isChecked();
                    int right = 0;
                    if (isPrive) {
                        right = 1;
                    }
                    String imgFilePath = Environment.getExternalStorageDirectory().toString()
                            + "/DCIM/Camera/a.jpg";


                    createRequest.create("91e65d721bc7fe4d4decd764c32d23db", albumName.getText().toString(), right, imgFilePath, new AlbumCreateRequest.UploadREsult() {
                        @Override
                        public void onSuccess(Response response) {
                            try {
                               String res = response.body().string();
                                toast(res+"成功");

                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void onError(Exception e) {

                            toast(e.getLocalizedMessage());
                        }
                    });
                }
            });
        }


        albunAdapter = new AlbunAdapter(getContext());
        albumGride.setAdapter(albunAdapter);
        albumGride.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getContext(), AlbumDetailActivity.class);
                Album album = (Album) adapterView.getItemAtPosition(i);
                intent.putExtra("albumId", album.id);
                getContext().startActivity(intent);
            }
        });

        albumGride.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                adapterView.getItemAtPosition(i);
                Log.d("相册长按", adapterView.getItemAtPosition(i).toString());
                Toast.makeText(getContext(), "相册长按", Toast.LENGTH_SHORT).show();
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("选择")
                        .setCancelable(true).setNegativeButton("设置fengmian", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                }).setPositiveButton("不知道ganma", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                builder.create().show();
                return false;
            }
        });



        try {
            AlbumRequest request = new AlbumRequest();

            request.get(AlbumResultBean.class, new JsonResultRequest.OnBeanResult() {
                @Override
                public void onSuccess(final RequestResultBean bean) {
                    if (albunAdapter != null) {

                        Activity activity = (Activity) getContext();

                        activity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                AlbumResultBean resultBean;
                                resultBean = (AlbumResultBean) bean;
                                albunAdapter.add(resultBean.data.data);
                                if (resultBean.data.data.size() > 0) {
                                    view.findViewById(R.id.album_create_view).setVisibility(View.GONE);
                                    view.findViewById(R.id.album_grade).setVisibility(View.VISIBLE);
                                }
                            }
                        });

                    }
                }

                @Override
                public void onFailure(Exception e) {

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_album, container, false);

        initView(view);


        return view;
    }

}
