package com.fagawee.mvp.net.cache;

import android.text.TextUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by Mr.Tian on 2020/1/14.
 */

public class CacheModel implements Serializable{

    public String message;
    public String protocol;
    public String mediaType;
    public String body;
    public HashMap headers=new HashMap();
    public int code;
    public long duration;
    public long date;




    @Override
    public String toString() {
        JSONObject jsonObject=new JSONObject();
        try {
            jsonObject.put("message",message);
            jsonObject.put("protocol",protocol);
            jsonObject.put("code",code);
            jsonObject.put("duration",duration);
            long time= System.currentTimeMillis();
            jsonObject.put("date",time);
            jsonObject.put("mediaType",mediaType);
            jsonObject.put("body",body);
            JSONArray jsonArray=new JSONArray();
            Iterator<HashMap.Entry<String,String>> iterator=headers.entrySet().iterator();
            while (iterator.hasNext())
            {
                JSONObject headers_=new JSONObject();
                HashMap.Entry<String,String> en=iterator.next();
                headers_.put("key",en.getKey());
                headers_.put("value",en.getValue());
                jsonArray.put(headers_);
            }

            jsonObject.put("headers",jsonArray);



        } catch (JSONException e) {
            e.printStackTrace();
        }

        return jsonObject.toString();
    }
    public static CacheModel fromString(String src)
    {
        if(TextUtils.isEmpty(src))
        {
            return null;
        }

        try {
            JSONObject jsonObject=new JSONObject(src);
            String message=jsonObject.getString("message");
            String protocol=jsonObject.getString("protocol");
            int  code=jsonObject.getInt("code");
            long  duration=jsonObject.getLong("duration");
            long  date=jsonObject.getLong("date");
            String mediaType=jsonObject.getString("mediaType");
            String body=jsonObject.getString("body");

            HashMap headerstemp=new HashMap();
            JSONArray headers_=jsonObject.getJSONArray("headers");
            if(headers_!=null)
            {
                for (int i = 0; i < headers_.length(); i++) {
                    JSONObject jsonObject1=headers_.getJSONObject(i);
                    String key=jsonObject1.getString("key");
                    String value=jsonObject1.getString("value");
                    headerstemp.put(key,value);

                }
            }



            CacheModel cacheModel=new CacheModel();
            cacheModel.message=message;
            cacheModel.protocol=protocol;
            cacheModel.code=code;
            cacheModel.duration=duration;
            cacheModel.date=date;
            cacheModel.mediaType=mediaType;
            cacheModel.body=body;
            cacheModel.headers=headerstemp;




            return cacheModel;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * 是否已经过期
     * @return
     */
    public boolean isExpire()
    {
       long time= System.currentTimeMillis();
       long d=time-(duration+date);
       return (d>0);
    }


}
