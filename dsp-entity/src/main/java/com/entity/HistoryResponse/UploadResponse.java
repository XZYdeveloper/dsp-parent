package com.entity.HistoryResponse;

import com.alibaba.fastjson.annotation.JSONType;

@JSONType(orders = {"status","url"})
public class UploadResponse {
    private int status;
    private String url;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
