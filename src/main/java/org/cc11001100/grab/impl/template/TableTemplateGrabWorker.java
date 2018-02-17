package org.cc11001100.grab.impl.template;

import org.cc11001100.entity.Proxy;
import org.cc11001100.grab.base.AbstractGrabWorker;
import org.cc11001100.util.SimpleScriptParser;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * 这些代理大多数都是使用表格布局，既然如此，那么就使用一个通用的模板来解析
 *
 * @author CC11001100
 */
public class TableTemplateGrabWorker extends AbstractGrabWorker {

    protected String lineSelector = "tr";
    protected Integer skip = 0;
    protected String columnSelector = "td";
    protected String ipSelector;
    protected String portSelector;
    protected String anonymousLevelSelector;
    protected String proxyTypeSelector;
    protected String locationSelector;

    @Override
    public List<Proxy> grab() {
        List<String> urlList = SimpleScriptParser.parseUrl(grabFromUrls);
        return urlList.stream().flatMap(this::parse).distinct().collect(toList());
    }

    protected Stream<Proxy> parse(String url) {
        Document document = downloadDocument(url);
        Elements trElt = document.select(lineSelector);
        return trElt.stream().skip(skip).map(elt -> {
            try {
                Elements fieldElt = elt.select(columnSelector);
                return new Proxy.Builder().ip(fieldElt.select(ipSelector).text())
                        .port(Integer.parseInt(fieldElt.select(portSelector).text()))
                        .anonymousLevel(parseAnonymousLevel(fieldElt.select(anonymousLevelSelector).text()))
                        .proxyType(parseHttpType(fieldElt.select(proxyTypeSelector).text()))
                        .location(fieldElt.select(locationSelector).text()).build();
            } catch (NumberFormatException e) {
//                e.printStackTrace();
            }
            return null;
        }).filter(Objects::nonNull);
    }

    public String getLineSelector() {
        return lineSelector;
    }

    public void setLineSelector(String lineSelector) {
        this.lineSelector = lineSelector;
    }

    public Integer getSkip() {
        return skip;
    }

    public void setSkip(Integer skip) {
        this.skip = skip;
    }

    public String getColumnSelector() {
        return columnSelector;
    }

    public void setColumnSelector(String columnSelector) {
        this.columnSelector = columnSelector;
    }

    public String getIpSelector() {
        return ipSelector;
    }

    public void setIpSelector(String ipSelector) {
        this.ipSelector = ipSelector;
    }

    public String getPortSelector() {
        return portSelector;
    }

    public void setPortSelector(String portSelector) {
        this.portSelector = portSelector;
    }

    public String getAnonymousLevelSelector() {
        return anonymousLevelSelector;
    }

    public void setAnonymousLevelSelector(String anonymousLevelSelector) {
        this.anonymousLevelSelector = anonymousLevelSelector;
    }

    public String getProxyTypeSelector() {
        return proxyTypeSelector;
    }

    public void setProxyTypeSelector(String proxyTypeSelector) {
        this.proxyTypeSelector = proxyTypeSelector;
    }

    public String getLocationSelector() {
        return locationSelector;
    }

    public void setLocationSelector(String locationSelector) {
        this.locationSelector = locationSelector;
    }
}
