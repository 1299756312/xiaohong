package com.tencent.wxcloudrun.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tencent.wxcloudrun.entity.Choices;
import com.tencent.wxcloudrun.entity.Root;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping({"/api"})
public class WxTestController {
    private static final Logger log = LoggerFactory.getLogger(WxTestController.class);

    @Value("${chat.config.key}")
    private String chatConfigkey;

    @Value("${chat.config.code}")
    private String chatConfigcode;

    @Value("${chat.config.appid}")
    private String wxMapAppId;

    @Value("${chat.config.secret}")
    private String wxMapSecret;

    @Value("${chat.config.openid}")
    private String wxMapOpenid;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping({"/sent"})
    public String getChatGPT(String msg) {
        long start = System.currentTimeMillis();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", "Bearer " + this.chatConfigkey);
        String url = "https://api.openai.com/v1/completions";
        Map<String, Object> params = new HashMap<>();
        params.put("model", this.chatConfigcode);
        params.put("prompt", msg);
        params.put("temperature", Integer.valueOf(0));
        params.put("max_tokens", Integer.valueOf(1000));
        HttpEntity<Map<String, Object>> entity = new HttpEntity(params, (MultiValueMap)headers);
        try {
            ResponseEntity<Root> responseEntity = this.restTemplate.postForEntity(url, entity, Root.class, params);
            Root body = (Root)responseEntity.getBody();
            List<Choices> choices = body.getChoices();
            Choices choices1 = choices.get(0);
            String text = choices1.getText();
            long stop = System.currentTimeMillis();
            long run = stop - start;
            return "AI解答：小程序耗时："+ run + " ms \n" + text;
        } catch (HttpClientErrorException e) {
            System.out.println(e.getMessage());
            log.info("e.getMessage()："+ e.getMessage());
                    log.info("ChatGPT资源耗尽，请尽快更换key");
            return "ChatGPT资源耗尽，请尽快更换key";
        }
    }
}
