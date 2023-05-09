//package BOOT-INF.classes.com.github.binarywang.demo.wx.mp.config;
//
//import com.github.binarywang.demo.wx.mp.config.WxMpProperties;
//import com.github.binarywang.demo.wx.mp.handler.KfSessionHandler;
//import com.github.binarywang.demo.wx.mp.handler.LocationHandler;
//import com.github.binarywang.demo.wx.mp.handler.LogHandler;
//import com.github.binarywang.demo.wx.mp.handler.MenuHandler;
//import com.github.binarywang.demo.wx.mp.handler.MsgHandler;
//import com.github.binarywang.demo.wx.mp.handler.NullHandler;
//import com.github.binarywang.demo.wx.mp.handler.ScanHandler;
//import com.github.binarywang.demo.wx.mp.handler.StoreCheckNotifyHandler;
//import com.github.binarywang.demo.wx.mp.handler.SubscribeHandler;
//import com.github.binarywang.demo.wx.mp.handler.UnsubscribeHandler;
//import java.util.List;
//import java.util.Map;
//import java.util.stream.Collectors;
//import me.chanjar.weixin.common.redis.JedisWxRedisOps;
//import me.chanjar.weixin.common.redis.WxRedisOps;
//import me.chanjar.weixin.mp.api.WxMpMessageHandler;
//import me.chanjar.weixin.mp.api.WxMpMessageRouter;
//import me.chanjar.weixin.mp.api.WxMpService;
//import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
//import me.chanjar.weixin.mp.config.WxMpConfigStorage;
//import me.chanjar.weixin.mp.config.impl.WxMpDefaultConfigImpl;
//import me.chanjar.weixin.mp.config.impl.WxMpRedisConfigImpl;
//import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
//import org.springframework.boot.context.properties.EnableConfigurationProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import redis.clients.jedis.JedisPool;
//import redis.clients.jedis.JedisPoolConfig;
//import redis.clients.jedis.util.Pool;
//
//@Configuration
//@EnableConfigurationProperties({WxMpProperties.class})
//public class WxMpConfiguration {
//    private final LogHandler logHandler;
//
//    private final NullHandler nullHandler;
//
//    private final KfSessionHandler kfSessionHandler;
//
//    private final StoreCheckNotifyHandler storeCheckNotifyHandler;
//
//    private final LocationHandler locationHandler;
//
//    private final MenuHandler menuHandler;
//
//    private final MsgHandler msgHandler;
//
//    private final UnsubscribeHandler unsubscribeHandler;
//
//    private final SubscribeHandler subscribeHandler;
//
//    private final ScanHandler scanHandler;
//
//    private final WxMpProperties properties;
//
//    public WxMpConfiguration(LogHandler logHandler, NullHandler nullHandler, KfSessionHandler kfSessionHandler, StoreCheckNotifyHandler storeCheckNotifyHandler, LocationHandler locationHandler, MenuHandler menuHandler, MsgHandler msgHandler, UnsubscribeHandler unsubscribeHandler, SubscribeHandler subscribeHandler, ScanHandler scanHandler, WxMpProperties properties) {
//        this.logHandler = logHandler;
//        this.nullHandler = nullHandler;
//        this.kfSessionHandler = kfSessionHandler;
//        this.storeCheckNotifyHandler = storeCheckNotifyHandler;
//        this.locationHandler = locationHandler;
//        this.menuHandler = menuHandler;
//        this.msgHandler = msgHandler;
//        this.unsubscribeHandler = unsubscribeHandler;
//        this.subscribeHandler = subscribeHandler;
//        this.scanHandler = scanHandler;
//        this.properties = properties;
//    }
//
//    @Bean
//    public WxMpService wxMpService() {
//        List<WxMpProperties.MpConfig> configs = this.properties.getConfigs();
//        if (configs == null)
//            throw new RuntimeException(");
//                    WxMpServiceImpl wxMpServiceImpl = new WxMpServiceImpl();
//        wxMpServiceImpl.setMultiConfigStorages((Map)configs
//                .stream().map(a -> {
//                    WxMpDefaultConfigImpl configStorage;
//                    if (this.properties.isUseRedis()) {
//                        WxMpProperties.RedisConfig redisConfig = this.properties.getRedisConfig();
//                        JedisPoolConfig poolConfig = new JedisPoolConfig();
//                        JedisPool jedisPool = new JedisPool((GenericObjectPoolConfig)poolConfig, redisConfig.getHost(), redisConfig.getPort().intValue(), redisConfig.getTimeout().intValue(), redisConfig.getPassword());
//                        WxMpRedisConfigImpl wxMpRedisConfigImpl = new WxMpRedisConfigImpl((WxRedisOps)new JedisWxRedisOps((Pool)jedisPool), a.getAppId());
//                    } else {
//                        configStorage = new WxMpDefaultConfigImpl();
//                    }
//                    configStorage.setAppId(a.getAppId());
//                    configStorage.setSecret(a.getSecret());
//                    configStorage.setToken(a.getToken());
//                    configStorage.setAesKey(a.getAesKey());
//                    return configStorage;
//                }).collect(Collectors.toMap(WxMpDefaultConfigImpl::getAppId, a -> a, (o, n) -> o)));
//        return (WxMpService)wxMpServiceImpl;
//    }
//
//    @Bean
//    public WxMpMessageRouter messageRouter(WxMpService wxMpService) {
//        WxMpMessageRouter newRouter = new WxMpMessageRouter(wxMpService);
//        newRouter.rule().handler((WxMpMessageHandler)this.logHandler).next();
//        newRouter.rule().async(false).msgType("event").event("kf_create_session")
//                .handler((WxMpMessageHandler)this.kfSessionHandler).end();
//        newRouter.rule().async(false).msgType("event").event("kf_close_session")
//                .handler((WxMpMessageHandler)this.kfSessionHandler).end();
//        newRouter.rule().async(false).msgType("event").event("kf_switch_session")
//                .handler((WxMpMessageHandler)this.kfSessionHandler).end();
//        newRouter.rule().async(false).msgType("event").event("poi_check_notify").handler((WxMpMessageHandler)this.storeCheckNotifyHandler).end();
//        newRouter.rule().async(false).msgType("event").event("CLICK").handler((WxMpMessageHandler)this.menuHandler).end();
//        newRouter.rule().async(false).msgType("event").event("VIEW").handler((WxMpMessageHandler)this.nullHandler).end();
//        newRouter.rule().async(false).msgType("event").event("subscribe").handler((WxMpMessageHandler)this.subscribeHandler).end();
//        newRouter.rule().async(false).msgType("event").event("unsubscribe").handler((WxMpMessageHandler)this.unsubscribeHandler).end();
//        newRouter.rule().async(false).msgType("event").event("LOCATION").handler((WxMpMessageHandler)this.locationHandler).end();
//        newRouter.rule().async(false).msgType("location").handler((WxMpMessageHandler)this.locationHandler).end();
//        newRouter.rule().async(false).msgType("event").event("SCAN").handler((WxMpMessageHandler)this.scanHandler).end();
//        newRouter.rule().async(false).handler((WxMpMessageHandler)this.msgHandler).end();
//        return newRouter;
//    }
//}
