package com.zhj.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * 登录日志实体类
 */
public class LoginLogVo implements Serializable {

	
	private static final long serialVersionUID = -1858994006365474353L;
	
	/**
	 * 登录日志Id
	 */
	private Long id;
	
	/**
	 * 用户id
	 */
	private Long userId;
	
	/**
	 * 用户名
	 */
	private String username;
	
	/**
	 * 登录ip
	 */
	private String loginIp;
	
	/**
	 * 登录时间
	 */
	private Date loginTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getLoginIp() {
		return loginIp;
	}

	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}

	public Date getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	

}
