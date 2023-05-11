package com.wx.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.istack.internal.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.json.AbstractJackson2HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.*;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;

@Configuration

public class RestProxyConfig {
    @Value("${rest.connection.isStartProxy}")
    private boolean isStartProxy;
    @Value("${rest.connection.proxyIP}")
    private String proxyIP;
    @Value("${rest.connection.port}")

    private Integer port;

    @Bean
    public RestTemplate restTemplate(ClientHttpRequestFactory factory) {

        RestTemplate restTemplate = new RestTemplate(factory);
        MyResponseErrorHandler errorHandler = new MyResponseErrorHandler();
        restTemplate.setErrorHandler((ResponseErrorHandler) errorHandler);
        restTemplate.getMessageConverters().add(new MyMappingJackson2HttpMessageConverter());
        return restTemplate;
    }

    //    创建 RestTemplate 时需要一个 ClientHttpRequestFactory，
    //    通过这个请求工厂，我们可以统一设置请求的超时时间，设置代理以及一些其他细节。
    //    通过下面代码配置后，我们直接在代码中注入 RestTemplate 就可以使用了。
    //    有时候我们还需要通过 ClientHttpRequestFactory 配置最大链接数，忽略SSL证书等，大家需要的时候可以自己查看代码设置。
    //   我们可以统一设置请求的超时时间
    @Bean
    public ClientHttpRequestFactory simpleClientHttpRequestFactory() {
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        if(this.isStartProxy){
            Proxy proxy = new Proxy(Proxy.Type.HTTP,new InetSocketAddress(proxyIP,port ));
            factory.setProxy(proxy);
        }
        return factory;
    }
    public class MyMappingJackson2HttpMessageConverter extends MappingJackson2HttpMessageConverter {
        public MyMappingJackson2HttpMessageConverter(){
            List<MediaType> mediaTypes = new ArrayList<>();
            mediaTypes.add(MediaType.TEXT_PLAIN);
            mediaTypes.add(MediaType.TEXT_HTML);  //加入text/html类型的支持
            setSupportedMediaTypes(mediaTypes);
        }
    }
    public List<String> sortGetTop3LongWords(@NotNull String sentence) {
        // 先切割句子，获取具体的单词信息
        String[] words = sentence.split(" ");
        List<String> wordList = new ArrayList<>();
        // 循环判断单词的长度，先过滤出符合长度要求的单词
        for (String word : words) {
            if (word.length() > 5) {
                wordList.add(word);
            }
        }
        // 对符合条件的列表按照长度进行排序
        wordList.sort(((o1, o2) -> o2.length()-o1.length()));
        // 判断list结果长度，如果大于3则截取前三个数据的子list返回
        if (wordList.size() > 3) {
            wordList = wordList.subList(0, 3);
        }
        return wordList;
    }


    public List<String> sortGetTop3LongWordsByStream(@NotNull String sentence) {
        return Arrays.stream(sentence.split(" "))
                .filter(word -> word.length() > 5)
                .sorted((o1, o2) -> o2.length() - o1.length())
                .limit(3)
                .collect(Collectors.toList());
    }


    class MyResponseErrorHandler extends DefaultResponseErrorHandler {

        @Override
        public boolean hasError(ClientHttpResponse response) throws IOException {
            return super.hasError(response);
        }

        @Override
        public void handleError(ClientHttpResponse response) throws IOException {
            HttpStatus statusCode = HttpStatus.resolve(response.getRawStatusCode());
            if (statusCode == null) {
                throw new UnknownHttpStatusCodeException(response.getRawStatusCode(), response.getStatusText(),
                        response.getHeaders(), getResponseBody(response), getCharset(response));
            }
            handleError(response, statusCode);
        }

        @Override
        protected void handleError(ClientHttpResponse response, HttpStatus statusCode) throws IOException {
            switch (statusCode.series()) {
                case CLIENT_ERROR:
                    HttpClientErrorException exp1 = new HttpClientErrorException(statusCode, response.getStatusText(), response.getHeaders(), getResponseBody(response), getCharset(response));
                    System.err.println("客户端调用异常" + exp1);
                    throw exp1;
                case SERVER_ERROR:
                    HttpServerErrorException exp2 = new HttpServerErrorException(statusCode, response.getStatusText(),
                            response.getHeaders(), getResponseBody(response), getCharset(response));
                    System.err.println("服务端调用异常" + exp2);
                    throw exp2;
                default:
                    UnknownHttpStatusCodeException exp3 = new UnknownHttpStatusCodeException(statusCode.value(), response.getStatusText(),
                            response.getHeaders(), getResponseBody(response), getCharset(response));
                    System.err.println("网络调用未知异常");
                    throw exp3;
            }
        }

    }
}