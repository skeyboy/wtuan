package xsk.com.wtuan.bean.file;

/**
 * Created by liyulong on 2018/1/18.
 */

public class FileBean {
    public String originName;
    public String name;
    public String path;

    @Override
    public String toString() {
        return "FileBean{" +
                "originName='" + originName + '\'' +
                ", name='" + name + '\'' +
                ", path='" + path + '\'' +
                '}';
    }
}
