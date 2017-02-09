package com.zhj.dao;

import com.zhj.vo.RoleVo;

public interface RoleVoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RoleVo record);

    int insertSelective(RoleVo record);

    RoleVo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RoleVo record);

    int updateByPrimaryKey(RoleVo record);
}