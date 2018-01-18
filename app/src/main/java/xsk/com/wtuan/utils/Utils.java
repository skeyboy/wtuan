package xsk.com.wtuan.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import xsk.com.wtuan.bean.file.FileResultBean;
import xsk.com.wtuan.global.PMBApplication;
import xsk.com.wtuan.net.PMBRequest;

/**
 * Created by liyulong on 2018/1/8.
 */

public class Utils {
    private static final String HOST = "http://127.0.0.1:1004/api/";
    private static final String V = "v1/";
    public static final String URI = HOST + V;

    public static boolean isEmpty(EditText editText) {
        String tmp = editText.getText().toString();
        return tmp.length() > 0;
    }

    public static void shortToast(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT)
                .show();
    }

    public static void run(String url, Map<String, String> paramaters, final Result result) {

        result.onStart();
        FormBody.Builder build = new FormBody.Builder();

        for (String key :
                paramaters.keySet()) {
            build.add(key, paramaters.get(key));
        }

        OkHttpClient client = new OkHttpClient();
        final Request request = new Request.Builder()
                .url(url)
                .post(build.build())
                .build();
        try {
            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    result.onFailure(e);

                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    result.onSuccess(response);

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            result.onFailure(e);
        }

    }

    private static String kStore = "kStore";

    public static void store(String token, String name, String password) {
        PMBApplication app = new PMBApplication();
        Context context = app.getApplicationContext();
        SharedPreferences preferences = context.getSharedPreferences(kStore, Context.MODE_PRIVATE);
        preferences.edit().putString("token", token);
        preferences.edit().putString("name", name);
        preferences.edit().putString("pwd", password);
    }

    public static String token() {
        PMBApplication app = new PMBApplication();
        Context context = app.getApplicationContext();
        SharedPreferences preferences = context.getSharedPreferences(kStore, Context.MODE_PRIVATE);
        return preferences.getString("token", "");
    }

    public static String name() {
        PMBApplication app = new PMBApplication();
        Context context = app.getApplicationContext();
        SharedPreferences preferences = context.getSharedPreferences(kStore, Context.MODE_PRIVATE);
        return preferences.getString("name", "");
    }

    public static String pwd() {
        PMBApplication app = new PMBApplication();
        Context context = app.getApplicationContext();
        SharedPreferences preferences = context.getSharedPreferences(kStore, Context.MODE_PRIVATE);
        return preferences.getString("pwd", "");
    }

    public static void runOnUiThread(Context context, Runnable runnable) {
        Activity activity = (Activity) context;
        activity.runOnUiThread(runnable);
    }

    public static void fileUpload(String path, final Upload upload) {
        File f = new File(path);
        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
        builder.addFormDataPart("img", f.getName(), RequestBody.create(MediaType.parse("image/*"), f));


        OkHttpClient client = new OkHttpClient();

        final Request request = new Request.Builder()
                .url(PMBRequest.API + "file")//地址
                .post(builder.build())//添加请求体
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                upload.onFailure();
            }

            @Override
            public void onResponse(Call call, Response response) {
                try {
                    String json = response.body().string();
                    Gson gson = new GsonBuilder().create();
                    FileResultBean bean = gson.fromJson(json, FileResultBean.class);
                    upload.onSuccess(bean);

                } catch (IOException e) {
                    e.printStackTrace();
                    upload.onFailure();
                }

            }
        });
    }

    public static String join(String sep, String... values) {
        StringBuffer buffer = new StringBuffer();
        for (String pic : values) {
            buffer.append(pic + ",");
        }

        return buffer.substring(0, buffer.substring(0).length() - 1);
    }

    public interface Upload {
        void onSuccess(FileResultBean response);

        void onFailure();
    }
}
