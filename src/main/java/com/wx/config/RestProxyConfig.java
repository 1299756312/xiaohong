
import com.fasterxml.jackson.databind.ObjectMapper;
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
import java.util.ArrayList;
import java.util.List;

@Configuration
public class RestProxyConfig {

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
        factory.setReadTimeout(5000); //读取超时时间
        factory.setConnectTimeout(15000); //连接超时时间
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