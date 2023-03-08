package com.wx.entity;

import lombok.Data;

/**
 * @Author 消息窗口
 */
@Data
public class Choice {
    private Message message;  //消息具体类
    private String finish_reason;  //结束原因
    private Integer index; //页码

}
