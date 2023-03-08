package com.wx.entity;

import lombok.Data;

/**
 * @Auther wushijie
 * chatgpt返回消息封装
 */
@Data
public class OpenAIRes {
    private  String id;  //消息id
    private  String object;  //消息类型
    private String model;  //消息模型
    private Usage usage;  //消息用量

}
