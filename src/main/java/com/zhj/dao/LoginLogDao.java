package com.zhj.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zhj.vo.LoginLogVo;

public interface LoginLogDao {
	
	public int insertUserLoginLog(LoginLogVo loginLogVo);
	
	/**
	 * 按条件查询列表
	 * @param loginLogVo
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	public List<LoginLogVo> findUserLoginLogs(@Param("loginLogVo") LoginLogVo loginLogVo, @Param("pageIndex") Integer pageIndex, @Param("pageSize") Integer pageSize);

	/**
	 * 按条件查询总条数
	 * @param loginLogVo
	 * @return
	 */
	public int findCountUserLoginLogs(@Param("loginLogVo") LoginLogVo loginLogVo);
}
