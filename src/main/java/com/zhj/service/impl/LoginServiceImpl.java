package com.zhj.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zhj.constants.MsgCodeConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.zhj.dao.LoginDao;
import com.zhj.service.LoginService;
import com.zhj.vo.User;

@Service("loginService")
public class LoginServiceImpl implements LoginService {
	
	@Autowired
	private LoginDao loginDao;
	
	public Map<String, Object> Login(User user) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		if(null != user && !StringUtils.isEmpty(user.getUsername()) && !StringUtils.isEmpty(user.getPassword())) {
			User u = new User();
			u.setUsername(user.getUsername());
			List<User> users= loginDao.getUserByCondition(u);
			if(null == users || users.size() == 0) {
				resMap.put("code", MsgCodeConstants.LOGIC_ERROR);
				resMap.put("msg", "用户不存在");
			} else {
				User backUser = users.get(0);
				if(!user.getUsername().equals(backUser.getUsername()) || !user.getPassword().equals(backUser.getPassword())) {
					resMap.put("code", MsgCodeConstants.LOGIC_ERROR);
					resMap.put("msg", "用户名或密码错误");
				}else {
					resMap.put("code", MsgCodeConstants.SUCCESS_CODE);
					resMap.put("msg", "查询成功");
					resMap.put("currentUser", backUser);
				}
			}
		} else {
			resMap.put("code", MsgCodeConstants.LOGIC_ERROR);
			resMap.put("msg", "用户名和密码不能为空");
		}
		return resMap;
	}

	public Map<String, String> insertUser(User user) {
		Map<String, String> resMap = new HashMap<String, String>();
		if(user == null || StringUtils.isEmpty(user.getUsername()) || StringUtils.isEmpty(user.getPassword())) {
			resMap.put("code", MsgCodeConstants.LOGIC_ERROR);
			resMap.put("msg", "用户名或密码不能为空");
			return resMap;
		}
		User u = new User();
		u.setUsername(user.getUsername());
		List<User> backUser = loginDao.getUserByCondition(u);
		if(null != backUser && backUser.size() > 0) {
			resMap.put("code", MsgCodeConstants.LOGIC_ERROR);
			resMap.put("msg", "该用户名已存在");
		} else {
			int userId = loginDao.addUser(user);
			if(userId > 0){
				resMap.put("code", MsgCodeConstants.SUCCESS_CODE);
				resMap.put("msg", "注册成功");
			} else {
				resMap.put("code", MsgCodeConstants.LOGIC_ERROR);
				resMap.put("msg", "注册失败");
				
			}
		}
		return resMap;
	}
	
}
