package org.cc11001100.grab.base;

import org.cc11001100.entity.Proxy;

import java.util.List;

/**
 * @author CC11001100
 */
public interface GrabWorker {

    /**
     *
     * 1. 返回的代理应该是自去重的
     *
     * @return
     */
    List<Proxy> grab();

    /**
     * 调度周期，单位是秒
     *
     * @return
     */
    int getInvokeIntervalSeconds();

    /**
     *
     * @return
     */
    String getName();

}
