package com.wx.entity.openAI;

import com.wx.entity.openAI.Message;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;

/**
 * @Author 吴世杰
 * OpenAI发送消息封装
 */
public class OpenAIAsk {
    @Value("${openAI.model}")
    private  String model ;  //训练模型
    private List<Message> message;  //消息类
    @Value("${openAI.temperature}")
    private float temperature;  //ai智能度，默认为1
    @Value("${openAI.top_p}")
    private float top_p; // 另一种标识智能度的方法，默认为1，两种智能度只能同时调整其中一种
    @Value("${openAI.n}")
    private Integer n;  //默认生成的聊天窗口
    @Value("${openAI.max_tokens}")
    private Integer max_tokens; //生成的消息量大小
    @Value("${openAI.presence_penalty}")
    private float presence_penalty; //默认为0，-2.0 和 2.0 之间的数字。正值会根据新标记在文本中的现有频率对其进行惩罚，从而降低模型逐字重复同一行的可能性。

    public OpenAIAsk(List<Message> message) {
        this.message = message;
    }

}
