package xsk.com.wtuan.net.request;


import xsk.com.wtuan.net.JsonResultRequest;

/**
 * Created by liyulong on 2018/1/9.
 */

public class SchoolRequest extends JsonResultRequest {


    @Override
    public String apiSmallURI() {
        return "/register/schoolList";
    }
}
