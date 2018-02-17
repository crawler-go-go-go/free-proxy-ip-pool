package org.cc11001100.grab.base;

import org.cc11001100.entity.Proxy;

import java.util.List;

/**
 * @author CC11001100
 */
@FunctionalInterface
public interface GrabWorker {

    /**
     *
     * 1. 返回的代理应该是自去重的
     * @return
     */
    List<Proxy> grab();

}
