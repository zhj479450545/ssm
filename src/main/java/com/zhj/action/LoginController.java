package com.zhj.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhj.constants.MsgCodeConstants;
import com.zhj.service.system.RoleService;
import com.zhj.utils.SessionUtil;
import com.zhj.vo.RoleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhj.service.LoginLogService;
import com.zhj.service.LoginService;
import com.zhj.vo.LoginLogVo;
import com.zhj.vo.User;

@Controller
@RequestMapping("web")
public class LoginController {
    @Autowired
    private LoginService loginService;
    @Autowired
    private LoginLogService loginLogService;
    @Autowired
    RoleService roleService;

    /**
     * 用户登录
     *
     * @param request
     * @param response
     * @param model
     * @param user
     * @return
     */
    @RequestMapping("login")
    @ResponseBody
    public Map<String, String> login(HttpServletRequest request, HttpServletResponse response, Model model, User user) {
        Map<String, String> resMap = new HashMap<String, String>();
        Map<String, Object> userMap = loginService.Login(user);
        if (MsgCodeConstants.SUCCESS_CODE.equals(userMap.get("code"))) {
            user = (User) userMap.get(SessionUtil.CURRENT_USER);
            LoginLogVo vo = new LoginLogVo();
            vo.setUserId(user.getId());
            vo.setLoginIp(request.getRemoteAddr());
            loginLogService.addUserLoginLog(vo);

            // 向Session中设置用户信息
            SessionUtil.setCuttentUser(request, user);
            List<RoleVo> roles = roleService.findRolesByUserId(user.getId());
            request.getSession().setAttribute(SessionUtil.CURRENT_ROLE, roles);
        }

        resMap.put("code", (String) userMap.get("code"));
        resMap.put("msg", (String) userMap.get("msg"));
        return resMap;
    }

    @RequestMapping("register")
    public String register(HttpServletRequest request, HttpServletResponse response, User user) {
        return "system/register";
    }

    /**
     * 新用户注册
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("doRegister")
    @ResponseBody
    public Map<String, String> doRegister(HttpServletRequest request, HttpServletResponse response, User user) {
        Map<String, String> resMap = loginService.insertUser(user);
        return resMap;
    }

    /**
     * 退出
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("loginOut")
    public String loginOut(HttpServletRequest request, HttpServletResponse response) {
        SessionUtil.removeCurrentUser(request);
        return "redirect:/" + request.getContextPath();
    }
}
