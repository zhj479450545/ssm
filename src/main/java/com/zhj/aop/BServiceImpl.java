package com.zhj.aop;

/**
 * 目标对象B
 * 由于此对象没有实现interface接口，故spring 会采用cglib代理
 * Created by Administrator on 2017/2/10.
 */
public class BServiceImpl {
    public void barB(String msg, int type){
        System.out.println("BServiceImpl.barB(msg:" + msg + " type:" + type + ")");
        if (type == 1)
            throw new IllegalArgumentException("测试异常");
    }
    public void fooB() {
        System.out.println("BServiceImpl.fooB()");
    }
}
