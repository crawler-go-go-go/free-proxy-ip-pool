package org.cc11001100.grab.base;

import org.cc11001100.entity.Proxy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author CC11001100
 */
public abstract class AbstractGrabWorker implements GrabWorker {

    /**
     * 调用间隔时间
     */
    protected Integer invokeIntervalSeconds = 60 * 60;

    /**
     * 当前正在抓取的网站的主页，在这里注册一下
     */
    protected String homeUrl;

    /**
     * 从当前这个网站上的哪些页面上抓取ip呢，在这里注册一下
     */
    protected String[] grabFromUrls;

    /**
     * 返回从{@link #grabFromUrls}中抓取到的内容，一
     * @return
     */
    public abstract List<Proxy> grab();

    protected Document downloadDocument(String url) {
        for (int i = 0; i < 5; i++) {
            try {
                return Jsoup.parse(new URL(url), 3000);
            } catch (IOException e) {
//                e.printStackTrace();
                sleep(500);
            }
        }
        return null;
    }

    protected int parseAnonymousLevel(String rawText) {
        if (rawText.contains("透明")) {
            return Proxy.ANONYMOUS_LEVEL_LOW;
        } else if (rawText.contains("普匿")) {
            return Proxy.ANONYMOUS_LEVEL_NORMAL;
        } else if (rawText.contains("高匿")) {
            return Proxy.ANONYMOUS_LEVEL_HIGH;
        } else {
            return -1;
        }
    }

    protected int parseHttpType(String rawContent) {
        String[] ss = rawContent.split(",");
        int res = 0;
        for (String s : ss) {
            if ("http".equals(s.trim().toLowerCase())) {
                res += 1;
            }
            if ("https".equals(s.trim().toLowerCase())) {
                res += 2;
            }
        }
        return res;
    }

    protected void sleep(long millis) {
        try {
            TimeUnit.MILLISECONDS.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public int getInvokeIntervalSeconds() {
        return invokeIntervalSeconds;
    }

    public void setInvokeIntervalSeconds(Integer invokeIntervalSeconds) {
        this.invokeIntervalSeconds = invokeIntervalSeconds;
    }

    public String getHomeUrl() {
        return homeUrl;
    }

    public void setHomeUrl(String homeUrl) {
        this.homeUrl = homeUrl;
    }

    public String[] getGrabFromUrls() {
        return grabFromUrls;
    }

    public void setGrabFromUrls(String[] grabFromUrls) {
        this.grabFromUrls = grabFromUrls;
    }

    @Override
    public String getName() {
        return homeUrl;
    }
}
