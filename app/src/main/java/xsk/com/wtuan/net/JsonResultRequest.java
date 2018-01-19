package xsk.com.wtuan.net;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.Map;

import okhttp3.Response;
import xsk.com.wtuan.bean.RequestResultBean;
import xsk.com.wtuan.utils.Result;

/**
 * Created by liyulong on 2018/1/9.
 */

public abstract class JsonResultRequest extends PMBRequest {
    public void get(Map<String, String> paramaters, final Class<?> clas, final OnBeanResult beanResult) {
        super.get(paramaters, new Result() {
            @Override
            public void onSuccess(Response response) {
                try {
                    String json = response.body().string();
                    Gson gson = new GsonBuilder().create();
                    RequestResultBean requestResultBean = (RequestResultBean) gson.fromJson(json, clas);
                    beanResult.onSuccess(requestResultBean);
                } catch (IOException e) {
                    e.printStackTrace();
                    beanResult.onFailure(e);
                }
            }

            @Override
            public void onFailure(Exception e) {
                beanResult.onFailure(e);
            }

            @Override
            public void onStart() {

            }
        });
    }

    public void get(final Class<?> clas, final OnBeanResult beanResult) {
        super.get(new Result() {
            @Override
            public void onSuccess(Response response) {
                try {
                    String json = response.body().string();
                    Gson gson = new GsonBuilder().create();
                    RequestResultBean requestResultBean = (RequestResultBean) gson.fromJson(json, clas);
                    beanResult.onSuccess(requestResultBean);
                } catch (IOException e) {
                    e.printStackTrace();
                    beanResult.onFailure(e);
                }
            }

            @Override
            public void onFailure(Exception e) {
                beanResult.onFailure(e);
            }

            @Override
            public void onStart() {

            }
        });
    }

    public void post(Map<String, String> paramaters, final Class<?> clas, final OnBeanResult beanResult) {
        post(paramaters, new Result() {
            @Override
            public void onSuccess(Response response) {
                try {
                    String json = response.body().string();
                    Gson gson = new GsonBuilder().create();
                    RequestResultBean requestResultBean = (RequestResultBean) gson.fromJson(json, clas);
                    beanResult.onSuccess(requestResultBean);
                } catch (IOException e) {
                    e.printStackTrace();
                    beanResult.onFailure(e);
                }
            }

            @Override
            public void onFailure(Exception e) {
                beanResult.onFailure(e);
            }

            @Override
            public void onStart() {

            }
        });
    }


    public interface OnBeanResult {
        void onSuccess(RequestResultBean bean);

        void onFailure(Exception e);
    }
}
