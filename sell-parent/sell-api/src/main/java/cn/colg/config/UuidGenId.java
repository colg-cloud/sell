package cn.colg.config;

import cn.hutool.core.util.RandomUtil;
import tk.mybatis.mapper.genid.GenId;

/**
 * 全局主键
 *
 * @author colg
 */
public class UuidGenId implements GenId<String> {

    @Override
    public String genId(String table, String column) {
        return RandomUtil.simpleUUID();
    }

}
