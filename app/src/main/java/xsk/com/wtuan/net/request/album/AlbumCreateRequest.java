package xsk.com.wtuan.net.request.album;

import java.io.File;
import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import xsk.com.wtuan.net.PMBRequest;

/**
 * Created by liyulong on 2018/1/17.
 */

public class AlbumCreateRequest {
    public void create(String apiToken, String albumName, int isPrivate, String path, final UploadREsult uploadREsult) {
        File f = new File(path);

        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
        builder.addFormDataPart("img", f.getName(), RequestBody.create(MediaType.parse("image/*"), f));

        builder.addFormDataPart("name", albumName);
        builder.addFormDataPart("isPrivate", String.valueOf(isPrivate));
        builder.addFormDataPart("api_token", apiToken);


        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(PMBRequest.API + "album/create")//地址
                .post(builder.build())//添加请求体
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                uploadREsult.onError(e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                uploadREsult.onSuccess(response);
            }
        });

    }

    public String apiSmallURI() {
        return "album/create";
    }
public interface UploadREsult{
    void onSuccess(Response response);

    void onError(Exception e);
}

}
