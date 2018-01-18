package xsk.com.wtuan.net.request.album;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import xsk.com.wtuan.bean.file.FileResultBean;
import xsk.com.wtuan.net.PMBRequest;
import xsk.com.wtuan.utils.Result;

/**
 * Created by liyulong on 2018/1/17.
 */

public class AlbumCreateRequest extends PMBRequest {

    public void create(final String apiToken, final String albumName, final int isPrivate, final String path, final UploadREsult uploadREsult) {

        albunCover(path, new Upload() {
            @Override
            public void onSuccess(Response response) {

                String json = "";
                FileResultBean fileResultBean;
                try {
                    json = response.body().string();
                    Gson gson = new GsonBuilder().create();
                    fileResultBean = gson.fromJson(json, FileResultBean.class);


                    HashMap<String, String> paramaters = new HashMap<>();
                    paramaters.put("name", albumName);
                    paramaters.put("isPrivate", String.valueOf(isPrivate));
                    paramaters.put("api_token", apiToken);
                    paramaters.put("cover", fileResultBean.data.path);

                    post(paramaters, new Result() {
                        @Override
                        public void onSuccess(Response response) {
                            uploadREsult.onSuccess(response);
                        }

                        @Override
                        public void onFailure(Exception e) {
                            uploadREsult.onError(e);
                        }

                        @Override
                        public void onStart() {

                        }
                    });

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure() {

            }
        });


    }

    private void albunCover(String path, final Upload upload) {

        File f = new File(path);
        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
        builder.addFormDataPart("img", f.getName(), RequestBody.create(MediaType.parse("image/*"), f));


        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(PMBRequest.API + "file")//地址
                .post(builder.build())//添加请求体
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                upload.onFailure();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                upload.onSuccess(response);
            }
        });


    }

    interface Upload {
        void onSuccess(Response response);

        void onFailure();
    }

    public String apiSmallURI() {
        return "album/create";
    }

    public interface UploadREsult {
        void onSuccess(Response response);

        void onError(Exception e);
    }

}
