package com.zhj.service;

import java.util.Map;

import com.zhj.vo.User;

public interface LoginService {
	
	public Map<String, Object> Login(User user);
	
	public Map<String, String> insertUser(User user);
}
