//package com.tencent.wxcloudrun.config;
//
//import org.springframework.boot.context.properties.ConfigurationProperties;
//
//@ConfigurationProperties(prefix = "wx.mp")
//public class WxMpProperties {
//    private boolean useRedis;
//
//    private RedisConfig redisConfig;
//
//    private List<MpConfig> configs;
//
//    public void setUseRedis(boolean useRedis) {
//        this.useRedis = useRedis;
//    }
//
//    public void setRedisConfig(RedisConfig redisConfig) {
//        this.redisConfig = redisConfig;
//    }
//
//    public void setConfigs(List<MpConfig> configs) {
//        this.configs = configs;
//    }
//
//    public boolean equals(Object o) {
//        if (o == this)
//            return true;
//        if (!(o instanceof com.github.binarywang.demo.wx.mp.config.WxMpProperties))
//            return false;
//        com.github.binarywang.demo.wx.mp.config.WxMpProperties other = (com.github.binarywang.demo.wx.mp.config.WxMpProperties)o;
//        if (!other.canEqual(this))
//            return false;
//        if (isUseRedis() != other.isUseRedis())
//            return false;
//        Object this$redisConfig = getRedisConfig(), other$redisConfig = other.getRedisConfig();
//        if ((this$redisConfig == null) ? (other$redisConfig != null) : !this$redisConfig.equals(other$redisConfig))
//            return false;
//        Object<MpConfig> this$configs = (Object<MpConfig>)getConfigs(), other$configs = (Object<MpConfig>)other.getConfigs();
//        return !((this$configs == null) ? (other$configs != null) : !this$configs.equals(other$configs));
//    }
//
//    protected boolean canEqual(Object other) {
//        return other instanceof com.github.binarywang.demo.wx.mp.config.WxMpProperties;
//    }
//
//    public int hashCode() {
//        int PRIME = 59;
//        result = 1;
//        result = result * 59 + (isUseRedis() ? 79 : 97);
//        Object $redisConfig = getRedisConfig();
//        result = result * 59 + (($redisConfig == null) ? 43 : $redisConfig.hashCode());
//        Object<MpConfig> $configs = (Object<MpConfig>)getConfigs();
//        return result * 59 + (($configs == null) ? 43 : $configs.hashCode());
//    }
//
//    public boolean isUseRedis() {
//        return this.useRedis;
//    }
//
//    public RedisConfig getRedisConfig() {
//        return this.redisConfig;
//    }
//
//    public List<MpConfig> getConfigs() {
//        return this.configs;
//    }
//
//    public String toString() {
//        return JsonUtils.toJson(this);
//    }
//}
