package com.fagawee.mvp.net.cache;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

import static okhttp3.Protocol.HTTP_1_1;

/**
 * Created by Mr.Tian on 2020/1/14.
 */

public class CacheInterceptor implements Interceptor{
    Cacher cacher;
    @Override
    public Response intercept(Chain chain) throws IOException {
        if(cacher==null)cacher=Cacher.instance;

        Request request=chain.request();
        boolean isContainCache=cacher.isContainCache(request);
        CacheModel cacheModel=cacher.getCache(request);
        Cacher.CacheType cacheType=cacher.getCacheConfig().cacheType(request);
        if(cacher.getCacheConfig().isCacheFilter(request))
        {

            if(cacheType != Cacher.CacheType.NoCache)
            {
                if(cacheModel!=null&&isContainCache&&!cacheModel.isExpire())
                {

                    Headers.Builder headersbuilder=new Headers.Builder();
                    Iterator<HashMap.Entry<String,String>> iterator=cacheModel.headers.entrySet().iterator();
                    while (iterator.hasNext())
                    {
                        HashMap.Entry<String,String> en=iterator.next();
                        headersbuilder.add(en.getKey(),en.getValue());


                    }


                    Protocol protocol=Protocol.get(cacheModel.protocol);
                    Response response=new Response.Builder()
                            .message(cacheModel.message)
                            .body(ResponseBody.create(MediaType.parse(cacheModel.mediaType),cacheModel.body))
                            .headers(headersbuilder.build())
                            .code(cacheModel.code)
                            .protocol(protocol)
                            .request(request)
                            .build();
                    return response;
                }
                else
                {
                    if(cacheType== Cacher.CacheType.FirstCacheElseNet)
                    {
                        return proceed(request,chain);
                    }
                    else if(cacheType== Cacher.CacheType.OnlyCache)
                    {

                        throw  new NoCacheException("localcache is null");
                        /*
                        Response response=new Response.Builder()
                                .message("NoCache")
                                .code(404)
                                .protocol(HTTP_1_1)
                                .request(request)
                                .body(ResponseBody.create(MediaType.parse(""),"NoCache"))
                                .build();
                        return response;
                        */
                    }
                    else{
                        return proceed(request,chain);
                    }

                }
            }
            else
            {
                return proceed(request,chain);
            }

        }
        else
        {
            return proceed(request,chain);
        }






    }


    public Response proceed(Request request,Chain chain)
    {
        try {
            Response response=chain.proceed(request);
            if(cacher==null)cacher=Cacher.instance;
            Cacher.CacheType cacheType=cacher.getCacheConfig().cacheType(request);
            if(cacheType != Cacher.CacheType.NoCache)
            {
                Cacher.ICacheConfig cacheConfig=cacher.getCacheConfig();
                if(cacheConfig.isCacheFilter(request))
                {
                    CacheModel cacheModel=new CacheModel();
                    cacheModel.duration=cacheConfig.getDuration(request);
                    cacheModel.message=response.message();
                    cacheModel.code=response.code();
                    cacheModel.protocol=response.protocol().toString();
                    Headers headers=response.headers();
                    for (int i = 0; i < headers.size(); i++) {
                        cacheModel.headers.put(headers.name(i),headers.value(i));
                    }
                    cacheModel.mediaType=response.body().contentType().toString();
                    cacheModel.body=response.body().string();
                    cacher.putCache(request,cacheModel);
                }

            }

            return response;
        } catch (IOException e) {

            return null;
        }
    }







}
