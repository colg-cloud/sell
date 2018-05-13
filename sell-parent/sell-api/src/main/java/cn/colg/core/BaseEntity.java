package cn.colg.core;

import java.io.Serializable;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * Entity 基础类
 *
 * @author colg
 */
public abstract class BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        return JSON.toJSONString(this,
            // 日期时间 毫秒 -> "yyyy-MM-dd HH:mm:ss"
            SerializerFeature.WriteDateUseDateFormat,
            // 输出值为null的字段
            SerializerFeature.WriteMapNullValue,
            // 消除对同一对象循环引用
            SerializerFeature.DisableCircularReferenceDetect);
    }
}
