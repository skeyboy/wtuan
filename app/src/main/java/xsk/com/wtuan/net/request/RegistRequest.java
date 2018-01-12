package xsk.com.wtuan.net.request;

import java.util.HashMap;

import xsk.com.wtuan.bean.RequestResultBean;
import xsk.com.wtuan.bean.regist.RegistResultBean;
import xsk.com.wtuan.net.JsonResultRequest;


/**
 * Created by liyulong on 2018/1/9.
 */

public class RegistRequest extends JsonResultRequest {

    public void regist(String userName, String password, final OnRegistResult result) {
        HashMap<String, String> paramaters = new HashMap<>();
        paramaters.put("userName", userName);
        paramaters.put("password", password);
        post(paramaters, RegistResultBean.class, new OnBeanResult() {
            @Override
            public void onSuccess(RequestResultBean bean) {

            }

            @Override
            public void onFailure(Exception e) {

            }
        });
    }


    @Override
    public String apiSmallURI() {
        return "/register/doReg";
    }

    public interface OnRegistResult{
        void onSuccess(RequestResultBean resultBean);

        void onFail(String meg);
    }
}
