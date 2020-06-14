package com.heit.SSM.model;

import java.util.List;

public class QUerydata {
    private int  code;
    private String msg;
    private List<QueryResp> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<QueryResp> getData() {
        return data;
    }

    public void setData(List<QueryResp> data) {
        this.data = data;
    }
}
