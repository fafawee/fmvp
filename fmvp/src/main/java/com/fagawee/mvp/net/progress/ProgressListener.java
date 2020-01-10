package com.fagawee.mvp.net.progress;

/**
 * Created by Mr.Tian on 2017/9/10.
 */

public interface ProgressListener {
    void onProgress(long soFarBytes, long totalBytes);

    void onError(Throwable throwable);
}
