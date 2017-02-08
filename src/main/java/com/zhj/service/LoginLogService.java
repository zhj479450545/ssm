package com.zhj.service;

import com.zhj.common.Bean.PageBean;
import com.zhj.vo.LoginLogVo;

public interface LoginLogService {
	
	public boolean addUserLoginLog(LoginLogVo loginLogVo);
	
	public PageBean<LoginLogVo> findUserLoginLogs(LoginLogVo loginLogVo, Integer pageNum, Integer pageSize);
}
