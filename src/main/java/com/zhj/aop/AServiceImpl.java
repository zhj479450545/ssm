package com.zhj.aop;

import com.zhj.common.Bean.PageBean;
import com.zhj.service.LoginLogService;
import com.zhj.service.LoginService;
import com.zhj.vo.LoginLogVo;

/**
 * 目标对象A
 * 由于此对象实现了interface接口，故spring 会采用jdk代理
 * Created by Administrator on 2017/2/10.
 */
public class AServiceImpl implements LoginLogService {

    public boolean addUserLoginLog(LoginLogVo loginLogVo) {
        return false;
    }

    public PageBean<LoginLogVo> findUserLoginLogs(LoginLogVo loginLogVo, Integer pageNum, Integer pageSize) {
        return null;
    }

    public void barA() {
        System.out.println("AServiceImpl.barA()");
    }
    public void fooA(String _msg) {
        System.out.println("AServiceImpl.fooA(msg:" + _msg + ")");
    }
}
