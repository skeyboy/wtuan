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

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

import okhttp3.Response;
import xsk.com.wtuan.R;
import xsk.com.wtuan.activity.AlbumDetailActivity;
import xsk.com.wtuan.adapter.AlbunAdapter;
import xsk.com.wtuan.bean.RequestResultBean;
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
    protected void initView(View view) {
        albumGride = view.findViewById(R.id.album_grade);
        albumCreat = view.findViewById(R.id.album_create);
        albumName = view.findViewById(R.id.album_name);
        albumPrivate = view.findViewById(R.id.album_private);
        if (Utils.isEmpty(albumName)) {

        }else {
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
                            toast(response.toString());
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

        String json = "{\n" +
                "    \"code\": 0,\n" +
                "    \"msg\": \"success\",\n" +
                "    \"data\": {\n" +
                "        \"currentPage\": 1,\n" +
                "        \"data\": [\n" +
                "            {\n" +
                "                \"id\": 9,\n" +
                "                \"name\": \"1111\",\n" +
                "                \"cover\": \"\",\n" +
                "                \"tuanId\": 1,\n" +
                "                \"addTime\": 1515393164,\n" +
                "                \"private\": 0,\n" +
                "                \"updateTime\": \"\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"id\": 8,\n" +
                "                \"name\": \"1111\",\n" +
                "                \"cover\": \"\",\n" +
                "                \"tuanId\": 1,\n" +
                "                \"addTime\": 1515392575,\n" +
                "                \"private\": 0,\n" +
                "                \"updateTime\": \"\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"id\": 7,\n" +
                "                \"name\": \"1111\",\n" +
                "                \"cover\": \"\",\n" +
                "                \"tuanId\": 1,\n" +
                "                \"addTime\": 1515392575,\n" +
                "                \"private\": 0,\n" +
                "                \"updateTime\": \"\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"id\": 6,\n" +
                "                \"name\": \"1111\",\n" +
                "                \"cover\": \"\",\n" +
                "                \"tuanId\": 1,\n" +
                "                \"addTime\": 1515392575,\n" +
                "                \"private\": 0,\n" +
                "                \"updateTime\": \"\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"id\": 5,\n" +
                "                \"name\": \"1111\",\n" +
                "                \"cover\": \"\",\n" +
                "                \"tuanId\": 1,\n" +
                "                \"addTime\": 1515392502,\n" +
                "                \"private\": \"\",\n" +
                "                \"updateTime\": \"\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"id\": 4,\n" +
                "                \"name\": \"1111\",\n" +
                "                \"cover\": \"\",\n" +
                "                \"tuanId\": 1,\n" +
                "                \"addTime\": 1515392502,\n" +
                "                \"private\": \"\",\n" +
                "                \"updateTime\": \"\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"id\": 3,\n" +
                "                \"name\": \"1111\",\n" +
                "                \"cover\": \"\",\n" +
                "                \"tuanId\": 1,\n" +
                "                \"addTime\": 1515392501,\n" +
                "                \"private\": \"\",\n" +
                "                \"updateTime\": \"\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"id\": 2,\n" +
                "                \"name\": \"1111\",\n" +
                "                \"cover\": \"\",\n" +
                "                \"tuanId\": 1,\n" +
                "                \"addTime\": 1515392497,\n" +
                "                \"private\": \"\",\n" +
                "                \"updateTime\": \"\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"id\": 1,\n" +
                "                \"name\": \"1111\",\n" +
                "                \"cover\": \"1111\",\n" +
                "                \"tuanId\": 1,\n" +
                "                \"addTime\": 1515392451,\n" +
                "                \"private\": \"\",\n" +
                "                \"updateTime\": 1515394052\n" +
                "            }\n" +
                "        ],\n" +
                "        \"firstPageUrl\": \"http://127.0.0.1:1004/api/v1/tuan/album/list?page=1\",\n" +
                "        \"from\": 1,\n" +
                "        \"lastPage\": 1,\n" +
                "        \"lastPageUrl\": \"http://127.0.0.1:1004/api/v1/tuan/album/list?page=1\",\n" +
                "        \"nextPageUrl\": \"\",\n" +
                "        \"path\": \"http://127.0.0.1:1004/api/v1/tuan/album/list\",\n" +
                "        \"perPage\": 15,\n" +
                "        \"prevPageUrl\": \"\",\n" +
                "        \"to\": 9,\n" +
                "        \"total\": 9\n" +
                "    }\n" +
                "}";
        try {
            Gson gson = new GsonBuilder().create();
//            AlbumResultBean requestResultBean = (AlbumResultBean) gson.fromJson(json, AlbumResultBean.class);
//            albunAdapter.add(requestResultBean.data.data);
//            albunAdapter.notifyDataSetChanged();
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        }

        AlbumRequest request = new AlbumRequest();

                request.get(AlbumResultBean.class, new JsonResultRequest.OnBeanResult() {
            @Override
            public void onSuccess(RequestResultBean bean) {
                if (albunAdapter != null) {
                    AlbumResultBean resultBean;
                    resultBean = (AlbumResultBean) bean;
                    albunAdapter.add(resultBean.data.data);
                }
            }

            @Override
            public void onFailure(Exception e) {

            }
        });
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
