package com.luoluohao.answer_system.util;

import java.util.UUID;

/**
 * @author : S_ven
 * @create : 2018/10/25 20:49
 * @describe: UUIDUtil
 */
public class UUIDUtil {

    /**
     * 自动生成32位的UUid，对应数据库的主键id进行插入用。
     *
     * @return
     */
    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

}
