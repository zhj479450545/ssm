package com.zhj.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhj.common.Bean.PageBean;
import com.zhj.constants.PageConstants;
import com.zhj.dao.LoginLogDao;
import com.zhj.service.LoginLogService;
import com.zhj.vo.LoginLogVo;


@Service("loginLogService")
public class LoginLogServiceImpl implements LoginLogService {

	@Autowired
	private LoginLogDao loginLogDao;
	
	public PageBean<LoginLogVo> findUserLoginLogs(LoginLogVo loginLogVo, Integer pageNum, Integer pageSize) {
		pageNum = pageNum == null ? PageConstants.DEFAULT_PAGE_NUM : pageNum;
		pageSize = pageSize == null ? PageConstants.DEFAULT_PAGE_SIZE : pageSize;
		Integer pageIndex = pageSize*(pageNum-1);
		int totalSize = loginLogDao.findCountUserLoginLogs(loginLogVo);
		List<LoginLogVo> loginLogs = loginLogDao.findUserLoginLogs(loginLogVo, pageIndex, pageSize);
		PageBean<LoginLogVo> pageBean = new PageBean<LoginLogVo>(loginLogs, pageNum, pageSize, totalSize);
		return pageBean;
	}

	public boolean addUserLoginLog(LoginLogVo loginLogVo) {
		int insertFlag = loginLogDao.insertUserLoginLog(loginLogVo);
		if(0 < insertFlag) {
			return true;
		}else {
			return false;
		}
	}

}
