package com.commonutils.kotyox.net.uploaddown;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;
import okio.ForwardingSource;
import okio.Okio;
import okio.Source;

/**
 * Created by chawei on 2018/8/23.
 *
 * 测试地址：http://www.wangjing.cn/app/WjBBS.apk
 */

public class ProgressResponseBody extends ResponseBody {
    private final ResponseBody responseBody;
    private final ProgressListener progressListener;
    private BufferedSource bufferedSource;

    public ProgressResponseBody(ResponseBody responseBody, ProgressListener progressListener) {
        this.responseBody = responseBody;
        this.progressListener = progressListener;
    }

    public MediaType contentType() {
        return this.responseBody.contentType();
    }

    public long contentLength() {
        return this.responseBody.contentLength();
    }

    public BufferedSource source() {
        if(this.bufferedSource == null) {
            this.bufferedSource = Okio.buffer(this.source(this.responseBody.source()));
        }

        return this.bufferedSource;
    }

    private Source source(Source source) {
        return new ForwardingSource(source) {
            long totalBytesRead = 0L;

            public long read(Buffer sink, long byteCount) {
                long bytesRead = 0L;

                try {
                    bytesRead = super.read(sink, byteCount);
                    this.totalBytesRead += bytesRead != -1L?bytesRead:0L;
                    if(ProgressResponseBody.this.progressListener != null) {
                        ProgressResponseBody.this.progressListener.update(this.totalBytesRead, ProgressResponseBody.this.responseBody.contentLength(), bytesRead == -1L, false);
                    }

                    return bytesRead;
                } catch (IOException var7) {
                    var7.printStackTrace();
                    if(ProgressResponseBody.this.progressListener != null) {
                        ProgressResponseBody.this.progressListener.update(this.totalBytesRead, 0L, bytesRead == -1L, true);
                    }

                    return -1L;
                }
            }
        };
    }
}