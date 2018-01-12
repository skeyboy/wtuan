package xsk.com.wtuan.activity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.engine.impl.PicassoEngine;

import java.io.File;
import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import xsk.com.wtuan.R;


public class CreateActivity extends AppCompatActivity {
    ImageView logoView;
    EditText nameV;
    EditText addrV;
    EditText descV;
    Uri logUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
        logoView = findViewById(R.id.create_logo);
        nameV = findViewById(R.id.create_name);
        addrV = findViewById(R.id.create_addr);
        descV = findViewById(R.id.create_desc);
    }

    private static final int REQUEST_CODE_CHOOSE = 111;


    public void choiceLogo(View view) {
        Matisse.from(CreateActivity.this)
                .choose(MimeType.of(MimeType.PNG)) // 选择 mime 的类型
                .countable(true)
                .maxSelectable(1) // 图片选择的最多数量
//                .gridExpectedSize(getResources().getDimensionPixelSize(R.dimen.grid_expected_size))
                .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)
                .thumbnailScale(0.85f) // 缩略图的比例
//                .imageEngine(new GlideEngine()) // 使用的图片加载引擎
                .imageEngine(new PicassoEngine())
                .forResult(REQUEST_CODE_CHOOSE); // 设置作为标记的请求码

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_CHOOSE && resultCode == RESULT_OK) {
            List<Uri> mSelected = Matisse.obtainResult(data);
            Log.d("Matisse", "mSelected: " + mSelected);
            logUri = mSelected.get(0);

            Picasso.with(this).load(logUri).into(logoView);
        }

    }

    public void createT(View view) {
        File f = new File("");

        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
        builder.addFormDataPart("img", f.getName(), RequestBody.create(MediaType.parse("image/*"), f));

        builder.addFormDataPart("name", nameV.getText().toString());
        builder.addFormDataPart("description", descV.getText().toString());
        builder.addFormDataPart("logo", addrV.getText().toString());


        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("")//地址
                .post(builder.build())//添加请求体
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

            }
        });

    }
}
