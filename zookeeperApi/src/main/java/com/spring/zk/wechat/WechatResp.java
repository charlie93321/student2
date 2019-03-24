package com.spring.zk.wechat;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;

/**
 * @AUTHOR: 小于
 * @DATE: [2019/3/14  19:04]
 * @DESC:
 */
@Data
public class WechatResp {
    /**
     * {"errcode":0,"errmsg":"ok","invaliduser":""}
     */
    private Integer errcode;
    private String errmsg;
    private String invaliduser;

    public boolean isOk() {
        return StringUtils.isEmpty(invaliduser) && errcode==0;
    }
}
