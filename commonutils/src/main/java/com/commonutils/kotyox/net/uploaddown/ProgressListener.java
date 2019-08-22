package com.commonutils.kotyox.net.uploaddown;

/**
 * Created by wei.
 * Date: 2019-04-24 22:08
 * Description:
 */
public interface ProgressListener {
    void update(long readBytes, long totalBytes, boolean isEnd, boolean b);
}
