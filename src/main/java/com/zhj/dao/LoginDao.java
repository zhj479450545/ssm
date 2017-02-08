package com.zhj.dao;

import java.util.List;

import com.zhj.vo.User;

public interface LoginDao {
	/**
	 * 条件查询用户表
	 * @param user
	 * @return
	 */
	public List<User> getUserByCondition(User user);
	
	/**
	 * 新增用户
	 * @param user
	 * @return
	 */
	public int addUser(User user);
}
