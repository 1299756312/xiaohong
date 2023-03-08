package com.wx.entity;

import lombok.Data;

/**
 * @Author wushijie
 * 消息用量类
 */
@Data
public class Usage {
    private Integer prompt_tokens; // 输入花费
    private Integer completion_tokens; //返回花费
    private Integer total_tokens;  //总花费

}
