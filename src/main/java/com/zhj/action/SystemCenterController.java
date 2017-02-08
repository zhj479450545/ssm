package com.zhj.action;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zhj.common.Bean.PageBean;
import com.zhj.service.LoginLogService;
import com.zhj.vo.LoginLogVo;
import com.zhj.vo.User;

/**
 * 系统中心
 * @author 周健
 *
 */
@Controller
@RequestMapping("system")
public class SystemCenterController {
	@Resource
	LoginLogService loginLogService;
	
	@RequestMapping("index")
	public String index(HttpServletRequest request, HttpServletResponse response, Model model, LoginLogVo loginLogVo, Integer pageNum, Integer pageSize) {
		User user = (User) request.getSession().getAttribute("currentUser");
		loginLogVo.setUserId(user.getId());
		PageBean<LoginLogVo> pageBean = getDatas(request, loginLogVo, pageNum, pageSize);
		model.addAttribute("currentUser", user);
		model.addAttribute("pageBean", pageBean);
		return "system/index";
	}
	
	@RequestMapping("list")
	public String list(HttpServletRequest request, HttpServletResponse response,Model model, LoginLogVo loginLogVo, Integer pageNum, Integer pageSize) {
		User user = (User) request.getSession().getAttribute("currentUser");
		loginLogVo.setUserId(user.getId());
		PageBean<LoginLogVo> pageBean = getDatas(request, loginLogVo, pageNum, pageSize);
		model.addAttribute("pageBean", pageBean);
		return "system/list";
	}
	
	private PageBean<LoginLogVo> getDatas(HttpServletRequest request, LoginLogVo loginLogVo, Integer pageNum, Integer pageSize){
		PageBean<LoginLogVo> pageBean = loginLogService.findUserLoginLogs(loginLogVo, pageNum, pageSize);
		return pageBean;
	}
}
