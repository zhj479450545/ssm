package com.zhj.utils;

import com.zhj.exception.BusinessException;
import com.zhj.vo.User;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Administrator on 2017/3/3.
 */
public class SessionUtil {

    public static final String CURRENT_USER = "currentUser";

    /**
     * 从Session中获取当前用户信息
     * @param request
     * @return
     */
    public static User getCurrentUser(HttpServletRequest request) {
        Object user = request.getSession().getAttribute(CURRENT_USER);
        if (null != user && user instanceof User) {
            return (User) user;
        } else {
            return null;
        }
    }

    /**
     * 将当前用户信息设置到Session中
     * @param request
     * @param user
     */
    public static void setCuttentUser(HttpServletRequest request, User user) {
        if (null != user) {
            request.getSession().setAttribute(CURRENT_USER, user);
        } else {
            throw new BusinessException("由于无法获取当前用户信息，故无法设置当前会话用户信息");
        }
    }

    /**
     * 从Session中移除当前用户信息
     * @param request
     */
    public static void removeCurrentUser(HttpServletRequest request) {
        request.getSession().removeAttribute(CURRENT_USER);
    }
}
