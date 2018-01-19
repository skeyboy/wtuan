package xsk.com.wtuan.core;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by liyulong on 2018/1/19.
 */
@Inherited
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Request {
    public String smallURI();

    public String api() default "api/v1/";

    public String host() default "http://api.xiangshike.com/";
}
