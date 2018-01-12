package xsk.com.wtuan.net.request;

import xsk.com.wtuan.net.JsonResultRequest;

/**
 * Created by liyulong on 2018/1/10.
 */

public class TuanListRequest extends JsonResultRequest {
    @Override
    public String apiSmallURI() {
        return "/tuan/list";
    }
}
