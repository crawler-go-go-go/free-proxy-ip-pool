package org.cc11001100.entity;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.util.Date;

/**
 * @author CC11001100
 */
public class Proxy {

    /**
     * ip
     */
    private String ip;

    /**
     * 端口
     */
    private Integer port;

    /**
     * 匿名级别， 0透明， 1普匿， 2高匿
     */
    private Integer anonymousLevel;

    /**
     * 代理类型，0 HTTP， 1 HTTPS
     */
    private Integer proxyType;

    /**
     * 响应毫秒数
     */
    private Integer responseMillis;

    /**
     * ip所处地
     */
    private String location;

    /**
     * 所属运营商
     */
    private String operation;

    /**
     * 上次测试有效性的时间
     */
    private Date lastTestTime;

    public Proxy() {
    }

    public Proxy(String ip, Integer port, Integer anonymousLevel, Integer proxyType, Integer responseMillis, String location, String operation, Date lastTestTime) {
        this.ip = ip;
        this.port = port;
        this.anonymousLevel = anonymousLevel;
        this.proxyType = proxyType;
        this.responseMillis = responseMillis;
        this.location = location;
        this.operation = operation;
        this.lastTestTime = lastTestTime;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public Integer getAnonymousLevel() {
        return anonymousLevel;
    }

    public void setAnonymousLevel(Integer anonymousLevel) {
        this.anonymousLevel = anonymousLevel;
    }

    public Integer getProxyType() {
        return proxyType;
    }

    public void setProxyType(Integer proxyType) {
        this.proxyType = proxyType;
    }

    public Integer getResponseMillis() {
        return responseMillis;
    }

    public void setResponseMillis(Integer responseMillis) {
        this.responseMillis = responseMillis;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Date getLastTestTime() {
        return lastTestTime;
    }

    public void setLastTestTime(Date lastTestTime) {
        this.lastTestTime = lastTestTime;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj instanceof Proxy) {
            Proxy p = (Proxy) obj;
            return StringUtils.equals(this.ip, p.ip) && (this.port != null && this.port.equals(p.port));
        }
        return false;
    }

    @Override
    public String toString() {
        return "Proxy{" +
                "ip='" + ip + '\'' +
                ", port=" + port +
                ", anonymousLevel=" + anonymousLevel +
                ", proxyType=" + proxyType +
                ", responseMillis=" + responseMillis +
                ", location='" + location + '\'' +
                ", operation='" + operation + '\'' +
                ", lastTestTime=" + lastTestTime +
                '}';
    }

    public static final Integer ANONYMOUS_LEVEL_HIGH = 2;
    public static final Integer ANONYMOUS_LEVEL_NORMAL = 1;
    public static final Integer ANONYMOUS_LEVEL_LOW = 0;

    public static final Integer PROXY_TYPE_HTTP = 1;
    public static final Integer PROXY_TYPE_HTTPS = 2;
    public static final Integer PROXY_TYPE_HTTP_HTTPS = 3;

    public static class Builder {

        private Proxy proxy = new Proxy();

        public Builder ip(String ip) {
            proxy.setIp(ip);
            return this;
        }

        public Builder port(int port) {
            proxy.setPort(port);
            return this;
        }

        public Builder anonymousLevel(int anonymousLevel) {
            proxy.setAnonymousLevel(anonymousLevel);
            return this;
        }

        public Builder proxyType(int proxyType) {
            proxy.setProxyType(proxyType);
            return this;
        }

        public Builder responseMillis(int responseMillis) {
            proxy.setResponseMillis(responseMillis);
            return this;
        }

        public Builder location(String location) {
            proxy.setLocation(location);
            return this;
        }

        public Builder operation(String operation) {
            proxy.setOperation(operation);
            return this;
        }

        public Builder lastTestTime(Date lastTestTime) {
            proxy.setLastTestTime(lastTestTime);
            return this;
        }

        public Proxy build() {
            return proxy;
        }

    }

}
