package xsk.com.wtuan.net.request.album;

import xsk.com.wtuan.net.JsonResultRequest;

/**
 * Created by liyulong on 2018/1/19.
 */

public class UserTuanList extends JsonResultRequest {
    @Override
    public String apiSmallURI() {
        return "user/tuan/list";
    }
}
