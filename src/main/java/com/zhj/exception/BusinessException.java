package com.zhj.exception;

/**
 * Created by Administrator on 2017/3/3.
 */
public class BusinessException extends RuntimeException {
    public BusinessException() {
        super();
    }

    public BusinessException(String errorMsg) {
        super(errorMsg);
    }
}
