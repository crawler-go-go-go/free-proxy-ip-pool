package org.cc11001100.grab.impl.template;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * 负责从配置文件中初始化代理网站grab worker
 *
 * @author CC11001100
 */
public class TableTemplateConfiguration {

    private String configFileLocation;

    private List<TableTemplateGrabWorker> getWhat;

    public TableTemplateConfiguration(String configFileLocation) {
        this.configFileLocation = configFileLocation;
    }

    public List<TableTemplateGrabWorker> get() {
        if (getWhat != null) {
            return getWhat;
        }

        JSONObject config;
        try {
            config = JSONObject.parseObject(FileUtils.readFileToString(new File(configFileLocation), "UTF-8"));
        } catch (IOException e) {
//            e.printStackTrace();
            return Collections.emptyList();
        }

        return getWhat = config.getJSONArray("grabList").stream().map(x -> {
            JSONObject grab = (JSONObject) x;
            TableTemplateGrabWorker worker = new TableTemplateGrabWorker();
            worker.setInvokeIntervalSeconds(grab.getIntValue("invokeIntervalSeconds"));
            worker.setGrabFromUrls(grab.getJSONArray("grabFromUrls").toArray(new String[]{}));
            worker.setHomeUrl(grab.getString("homeUrl"));

            worker.setLineSelector(grab.getString("lineSelector"));
            worker.setSkip(grab.getIntValue("skip"));
            worker.setColumnSelector(grab.getString("columnSelector"));
            worker.setIpSelector(grab.getString("ipSelector"));
            worker.setPortSelector(grab.getString("portSelector"));
            worker.setAnonymousLevelSelector(grab.getString("anonymousLevelSelector"));
            worker.setProxyTypeSelector(grab.getString("proxyTypeSelector"));
            worker.setLocationSelector(grab.getString("locationSelector"));

            return worker;
        }).collect(toList());
    }

}
