package com.fagawee.mvp.kit;

/**
 * Created by Mr.Tian on 2019/6/14.
 */

public class NumberUtils {
    public static long toLong(String src, long defaultNum)
    {
        try {
            return Long.parseLong(src);
        }
        catch (Exception e)
        {
            return defaultNum;
        }
    }
    public static long toLong(String src)
    {
       return toLong(src,-1);
    }
    public static int toInt(String src, int defaultNum)
    {
        try {
            return Integer.parseInt(src);
        }
        catch (Exception e)
        {
            return defaultNum;
        }
    }
    public static float toFloat(String src, float defaultNum)
    {
        try {
            return Float.parseFloat(src);
        }
        catch (Exception e)
        {
            return defaultNum;
        }
    }
    public static int toInt(String src)
    {
      return   toInt(src,-1);
    }
    public static float toFloat(String src)
    {
        return   toFloat(src,-1);
    }
}
