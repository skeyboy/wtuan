package xsk.com.wtuan.net;

import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import xsk.com.wtuan.utils.Result;

/**
 * Created by liyulong on 2018/1/9.
 */

public abstract class PMBRequest {
    //    private String API = "http://127.0.0.1:1004/api/v1/";
    public static final String API = "http://api.xiangshike.com/api/v1/";
    public static final String token = "91e65d721bc7fe4d4decd764c32d23db";
    private Map<String, String> paramaters;

    public abstract String apiSmallURI();

    protected String debugToken() {
        return "&api_token=91e65d721bc7fe4d4decd764c32d23db";
    }

    protected void get(final Result result) {
        result.onStart();
        OkHttpClient client = new OkHttpClient();

        String url = API + apiSmallURI();
        if (!url.endsWith("?")) {
            url += "?" + debugToken();
        }
        Log.d("url", url);
        final Request request = new Request.Builder()
                .url(url)
                .build();
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
    }

    /**
     * http://127.0.0.1:1004/api/v1/register/doReg
     *
     * @param paramaters
     * @param result
     */
    protected void get(Map<String, String> paramaters, final Result result) {
        result.onStart();
        ArrayList<String> p = new ArrayList<>();
        for (String key :
                paramaters.keySet()) {
            p.add(key + "=" + paramaters.get(key));
        }
        p.add("api_token=" + token);
        String append = join("&",   p.toArray());
        OkHttpClient client = new OkHttpClient();
        final Request request = new Request.Builder()
                .url(API + apiSmallURI() + "?" + append)
                .build();
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
    }

    protected static String join(String join, Object[] strAry) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < strAry.length; i++) {
            if (i == (strAry.length - 1)) {
                sb.append(strAry[i]);
            } else {
                sb.append(strAry[i]).append(join);
            }
        }

        return new String(sb);
    }

    /**
     * http://127.0.0.1:1004/api/v1/register/doReg
     *
     * @param paramaters
     * @param result
     */
    protected void post(Map<String, String> paramaters, final Result result) {
        result.onStart();
        FormBody.Builder builder = new FormBody.Builder();
        for (String key :
                paramaters.keySet()) {
            builder.add(key, paramaters.get(key));
        }
        builder.add("api_token", token);

        OkHttpClient client = new OkHttpClient();
        final Request request = new Request.Builder()
                .url(API + apiSmallURI())
                .post(builder.build()).build();
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

    }
}
