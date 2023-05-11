package com.wx.entity;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
@Component
@ConfigurationProperties(prefix = "chat.config")
public class Config {
    private String key;

    private String msg;

    private String code;

    public void setKey(String key) {
        this.key = key;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Config))
            return false;
        Config other = (Config)o;
        if (!other.canEqual(this))
            return false;
        Object this$key = getKey(), other$key = other.getKey();
        if ((this$key == null) ? (other$key != null) : !this$key.equals(other$key))
            return false;
        Object this$msg = getMsg(), other$msg = other.getMsg();
        if ((this$msg == null) ? (other$msg != null) : !this$msg.equals(other$msg))
            return false;
        Object this$code = getCode(), other$code = other.getCode();
        return !((this$code == null) ? (other$code != null) : !this$code.equals(other$code));
    }

    protected boolean canEqual(Object other) {
        return other instanceof Config;
    }

//    public int hashCode() {
//        int PRIME = 59;
//        result = 1;
//        Object $key = getKey();
//        result = result * 59 + (($key == null) ? 43 : $key.hashCode());
//        Object $msg = getMsg();
//        result = result * 59 + (($msg == null) ? 43 : $msg.hashCode());
//        Object $code = getCode();
//        return result * 59 + (($code == null) ? 43 : $code.hashCode());
//    }

    public String toString() {
        return "Config(key=" + getKey() + ", msg=" + getMsg() + ", code=" + getCode() + ")";
    }

    public String getKey() {
        return this.key;
    }

    public String getMsg() {
        return this.msg;
    }

    public String getCode() {
        return this.code;
    }
}
