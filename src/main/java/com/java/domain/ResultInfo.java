package com.java.domain;

/**
 * 消息结果返回信息
 * */
public class ResultInfo {
    //标志 true表示成功 false表示失败
    private boolean flag;
    //错误信息
    private String msg;

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }


}
