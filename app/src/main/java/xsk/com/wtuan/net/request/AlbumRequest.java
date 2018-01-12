package xsk.com.wtuan.net.request;

import xsk.com.wtuan.net.JsonResultRequest;

/**
 * Created by liyulong on 2018/1/10.
 */

public class AlbumRequest extends JsonResultRequest {
    @Override
    public String apiSmallURI() {
        return "/tuan/album/list";
    }
}
