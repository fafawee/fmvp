package com.fagawee.mvp.net.cache;

import okhttp3.Request;

/**
 * Created by Mr.Tian on 2020/1/14.
 */

public interface ICacher {

    CacheModel getCache(Request request);
    void putCache(Request request,CacheModel cacheModel);
    boolean isContainCache(Request request);
    void clearAllCache();



}
