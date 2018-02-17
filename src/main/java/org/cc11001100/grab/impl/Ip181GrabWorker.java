package org.cc11001100.grab.impl;

import org.cc11001100.entity.Proxy;
import org.cc11001100.grab.base.AbstractGrabWorker;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * http://www.ip181.com/daili/100.html
 *
 * @author CC11001100
 */
public class Ip181GrabWorker extends AbstractGrabWorker {

    public Ip181GrabWorker() {
        homeUrl = "http://www.ip181.com/";
        grabFromUrls = new String[]{
                "http://www.ip181.com/",
                "http://www.ip181.com/daili/%d.html"
        };
        invokeIntervalSeconds = 60 * 10;
    }

    public List<Proxy> grab() {
        List<Proxy> res = parse(grabFromUrls[0]);

        for (int i = 1; i <= 10; i++) {
            sleep(500);
            res.addAll(parse(String.format(grabFromUrls[1], i)));
        }

        return res.stream().distinct().collect(toList());
    }

    private List<Proxy> parse(String url) {
        Document document = downloadDocument(url);
        Elements trElt = document.select("tbody:contains(最近验证时间) tr");
        return trElt.stream().skip(1).map(elt -> {
            Elements fieldElt = elt.select("td");
            return new Proxy.Builder().ip(fieldElt.get(0).text())
                    .port(Integer.parseInt(fieldElt.get(1).text()))
                    .anonymousLevel(parseAnonymousLevel(fieldElt.get(2).text()))
                    .proxyType(parseHttpType(fieldElt.get(3).text()))
                    .location(fieldElt.get(5).text()).build();
        }).collect(toList());
    }

    public static void main(String[] args) {

        List<Proxy> res = new Ip181GrabWorker().grab();
        res.forEach(System.out::println);
        System.out.println("size: " + res.size());

    }

}
