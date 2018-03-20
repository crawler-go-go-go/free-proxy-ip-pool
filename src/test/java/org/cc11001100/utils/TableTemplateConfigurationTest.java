package org.cc11001100.utils;

import org.cc11001100.grab.impl.template.TableTemplateConfiguration;
import org.cc11001100.grab.impl.template.TableTemplateGrabWorker;
import org.junit.Test;

import java.util.List;

/**
 * @author CC11001100
 */
public class TableTemplateConfigurationTest {

    @Test
    public void test() {

        List<TableTemplateGrabWorker> workerList = new TableTemplateConfiguration("D:\\idea_study_workspace\\FreeProxyIpPool\\src\\main\\resources\\table-template-config.json").get();
        workerList.forEach(System.out::print);

    }

}
