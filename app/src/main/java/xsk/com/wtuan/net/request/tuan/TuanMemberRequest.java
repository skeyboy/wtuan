package xsk.com.wtuan.net.request.tuan;

import xsk.com.wtuan.core.Request;
import xsk.com.wtuan.net.JsonResultRequest;

/**
 * Created by liyulong on 2018/1/19.
 */

@Request(smallURI = "tuan/user/list")
public class TuanMemberRequest extends JsonResultRequest {
    @Override
    public String apiSmallURI() {
        return "tuan/user/list";
    }
}
