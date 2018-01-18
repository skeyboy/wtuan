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
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.engine.impl.PicassoEngine;

import java.util.HashMap;
import java.util.List;

import xsk.com.wtuan.R;
import xsk.com.wtuan.bean.RequestResultBean;
import xsk.com.wtuan.bean.file.FileResultBean;
import xsk.com.wtuan.net.JsonResultRequest;
import xsk.com.wtuan.net.request.TuanCreateRequest;
import xsk.com.wtuan.utils.Utils;


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
                .choose(MimeType.allOf()) // 选择 mime 的类型
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
        if (logUri == null) {
            Toast.makeText(this, "请选择对应的社团图标", Toast.LENGTH_SHORT).show();
            return;
        }
        Utils.fileUpload(this, logUri, new Utils.Upload() {
            @Override
            public void onSuccess(FileResultBean response) {
                HashMap<String, String> paramaters = new HashMap<>();
                paramaters.put("name", nameV.getText().toString());
                paramaters.put("description", descV.getText().toString());
                paramaters.put("logo", response.data.path);
                paramaters.put("category_id", "1");

                TuanCreateRequest request = new TuanCreateRequest();
                request.post(paramaters, RequestResultBean.class, new JsonResultRequest.OnBeanResult() {
                    @Override
                    public void onSuccess(final RequestResultBean bean) {
                       runOnUiThread(new Runnable() {
                           @Override
                           public void run() {
                               Toast.makeText(CreateActivity.this, bean.toString(), Toast.LENGTH_SHORT).show();
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