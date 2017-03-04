package com.zhj.service.system.impl;

import com.zhj.dao.system.RoleVoMapper;
import com.zhj.service.system.RoleService;
import com.zhj.vo.RoleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2017/3/4.
 */
@Service("roleService")
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleVoMapper roleVoMapper;

    /**
     * 根据父级id查询子角色
     * @param userId
     * @return
     */
    public List<RoleVo> findRolesByUserId(Long userId) {
        return roleVoMapper.selectRolesByUserId(userId);
    }
}
