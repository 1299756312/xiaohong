//package com.wx.service;
//
//import com.alibaba.fastjson.JSONObject;
//import com.soecode.wxtools.bean.WxXmlMessage;
//import com.soecode.wxtools.util.xml.XStreamTransformer;
//import com.thoughtworks.xstream.XStream;
//import com.wx.config.OpenAIAsk;
//import com.wx.entity.openAI.Choice;
//import com.wx.entity.openAI.Message;
//import com.wx.entity.openAI.OpenAIRes;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.http.HttpEntity;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.MediaType;
//import org.springframework.stereotype.Service;
//import org.springframework.web.client.RestTemplate;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//
//@Service
//public class OpenAIService {
//    @Autowired
//    RestTemplate restTemplate;
//    @Value("${openAI.key}")
//    private  String Apikey;
//
//    @Autowired
//    private  OpenAIAsk openAIAsk;
//
//    /**
//     * 调用openAI
//     */
//    public OpenAIRes sendOpenAI(String content) {
//        //封装消息发送体
//        JSONObject json = new JSONObject();
//        List<Message> messages = new ArrayList<>();
//        Message message = new Message();
//        message.setRole("user");
//        message.setContent(content);
//        messages.add(message);
//        openAIAsk.setMessages(messages);
//        String s = json.toJSONString(openAIAsk);
//        Map map = json.parseObject(s, Map.class);
//        json.putAll(map);
//        //发送http请求
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        headers.set(HttpHeaders.CONTENT_TYPE, "application/json; charset=utf-8");
//        headers.set(HttpHeaders.ACCEPT, "application/json");
//        headers.add("Authorization","Bearer "+Apikey);
//        HttpEntity<JSONObject> request = new HttpEntity<>(json, headers);
//        OpenAIRes res = restTemplate.postForObject("https://api.openai.com/v1/chat/completions", request, OpenAIRes.class);
//        return res;
//
//    }
//
//
//    /**
//     * 处理所有的事件和消息的回复,返回的是xml数据包
//     */
//    public String getRes(WxXmlMessage wx) {
//
//
//        String FromUserName = wx.getFromUserName();
//        String ToUserName = wx.getToUserName();
//        wx.setFromUserName(ToUserName);
//        wx.setToUserName(FromUserName);
//        wx.setCreateTime(System.currentTimeMillis()/1000);
//        long startTime = System.currentTimeMillis();
//        OpenAIRes openAIRes = sendOpenAI(wx.getContent());
//        long EndTime = System.currentTimeMillis();
//        if(EndTime-startTime>4.2*1000){
//            wx.setMsgType("");
//            return "";
//        }
//        List<Choice> choices = openAIRes.getChoices();
//        StringBuilder sb = new StringBuilder();
//        for (Choice choice :choices){
//            sb.append(choice.getMessage().getContent());
//        }
//        wx.setContent(sb.toString());
//        String res = XStreamTransformer.toXml(WxXmlMessage.class, wx);
//
//
//        return res;
//    }
//}
