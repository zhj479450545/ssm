package com.zhj.dao.system;

import com.zhj.vo.RoleVo;

import java.util.List;

public interface RoleVoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RoleVo record);

    int insertSelective(RoleVo record);

    RoleVo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RoleVo record);

    int updateByPrimaryKey(RoleVo record);

    List<RoleVo> selectRolesByUserId(Long userId);

}