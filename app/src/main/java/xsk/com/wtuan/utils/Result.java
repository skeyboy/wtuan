package xsk.com.wtuan.utils;

import okhttp3.Response;

/**
 * Created by liyulong on 2018/1/8.
 */

public interface Result {
        void onSuccess(Response response);

        void onFailure(Exception e);

        void onStart();

}
