package xsk.com.wtuan.bean.file;

import xsk.com.wtuan.bean.RequestResultBean;

/**
 * Created by liyulong on 2018/1/18.
 */

public class FileResultBean extends RequestResultBean {
    public FileBean data;

    @Override
    public String toString() {
        return "FileResultBean{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
