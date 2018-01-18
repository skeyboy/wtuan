package xsk.com.wtuan.net.request.album;

import xsk.com.wtuan.net.JsonResultRequest;

/**
 * Created by liyulong on 2018/1/18.
 */

public class AlbumAddPicRequest extends JsonResultRequest {

    @Override
    public String apiSmallURI() {
        return "album/addPicToAlbum";
    }

}
