package xsk.com.wtuan.net.request.album;

import xsk.com.wtuan.net.JsonResultRequest;

/**
 * Created by liyulong on 2018/1/12.
 */

public class AlbumCoverChangeRequest extends JsonResultRequest{
    @Override
    public String apiSmallURI() {
        return "/album/changeCover";
    }
}
