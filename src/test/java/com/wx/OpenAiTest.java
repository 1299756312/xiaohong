package com.wx;

import com.wx.entity.openAI.OpenAIRes;
import com.wx.service.OpenAIService;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.Random;

@SpringBootTest(classes = Main.class)
public class OpenAiTest {
        @Autowired
    OpenAIService openAIService;
        @Test
    public  void  sendApiTest(){
            OpenAIRes openAIRes = openAIService.sendOpenAI("你好");
            System.out.println(openAIRes.toString());
        }
        @Test
    public void getRandom(){
            for (int i = 0; i < 10; i++) {
                System.out.println(RandomStringUtils.randomAlphanumeric(12).toString());
            }




        }


}
