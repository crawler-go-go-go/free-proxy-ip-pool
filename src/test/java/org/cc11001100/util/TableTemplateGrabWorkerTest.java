package org.cc11001100.util;

import org.cc11001100.entity.Proxy;
import org.cc11001100.grab.impl.template.TableTemplateConfiguration;
import org.cc11001100.grab.impl.template.TableTemplateGrabWorker;
import org.junit.Test;

import java.util.List;

/**
 * @author CC11001100
 */
public class TableTemplateGrabWorkerTest {

    @Test
    public void testConfigFileGrab(){

        List<TableTemplateGrabWorker> workerList = new TableTemplateConfiguration("D:\\idea_study_workspace\\FreeProxyIpPool\\src\\main\\resources\\table-template-config.json").get();
        List<Proxy> proxyList = workerList.get(0).grab();
        proxyList.forEach(System.out::println);


    }

}
