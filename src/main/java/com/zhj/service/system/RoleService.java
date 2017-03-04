package com.zhj.service.system;

import com.zhj.vo.RoleVo;

import java.util.List;

/**
 * 角色管理service
 * Created by Administrator on 2017/3/4.
 */
public interface RoleService {
    List<RoleVo> findRolesByUserId(Long userId);

}
