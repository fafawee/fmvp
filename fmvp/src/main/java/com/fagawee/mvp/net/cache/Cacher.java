package com.fagawee.mvp.net.cache;

import android.app.Application;

import com.fagawee.mvp.BaseApp;
import com.fagawee.mvp.cache.DiskCache;

import okhttp3.Request;
import okhttp3.internal.cache.DiskLruCache;

/**
 * Created by Mr.Tian on 2020/1/14.
 */

public class Cacher implements ICacher{

    private ICacheConfig cacheConfig;

    private Application application;
    private long duration=Long.MAX_VALUE;
    public static  Cacher instance=new Cacher();

    private Cacher() {
    }


    public static void init(Application application)
    {
        instance.application=application;

    }



    public ICacheConfig getCacheConfig() {
        if(cacheConfig==null)
        {
            cacheConfig=new DefaultKeyBuilder();
        }
        return cacheConfig;
    }

    public void setCacheConfig(ICacheConfig cacheConfig) {
        this.cacheConfig = cacheConfig;
    }

    @Override
    public CacheModel getCache(Request request) {
        try {
            String key=getCacheConfig().getKey(request);
            DiskCache cache=DiskCache.getInstance(BaseApp.getInstance());
            String result=cache.get(key);
            return CacheModel.fromString(result);
        }
        catch (Exception e)
        {
            return null;
        }

    }

    @Override
    public void putCache(Request request, CacheModel cacheModel) {
        String key=getCacheConfig().getKey(request);
        DiskCache cache=DiskCache.getInstance(BaseApp.getInstance());
        cache.put(key,cacheModel.toString());
    }

    @Override
    public boolean isContainCache(Request request) {
        try {
            String key=getCacheConfig().getKey(request);
            DiskCache cache=DiskCache.getInstance(BaseApp.getInstance());
            boolean iscontain=cache.contains(key);

            return iscontain;
        }
        catch (Exception e)
        {
            return false;
        }

    }

    @Override
    public void clearAllCache() {
        DiskCache cache=DiskCache.getInstance(BaseApp.getInstance());
        cache.clear();
    }


    public enum CacheType{
        OnlyCache,
        NoCache,
        FirstCacheElseNet
    }


    public static interface  ICacheConfig
    {
        //缓存的键 存入本地文件中
         String getKey(Request request);
         //缓存的过期时间
         long getDuration(Request request);
         //哪些请求需要加入缓存
         boolean isCacheFilter(Request request);
         //缓存策略
        CacheType cacheType(Request request);
    }
    public class DefaultKeyBuilder implements ICacheConfig
    {

        @Override
        public String getKey(Request request) {
            return request.url().toString();
        }

        @Override
        public long getDuration(Request request) {
            return duration;
        }

        @Override
        public boolean isCacheFilter(Request request) {
            return true;
        }

        @Override
        public CacheType cacheType(Request request) {
            return CacheType.FirstCacheElseNet;
        }
    }

}
