package com.wx.entity;

import lombok.Data;

@Data
public class Message {
    private String role;  //讲述人
    private String context;  //讲述内容
}
