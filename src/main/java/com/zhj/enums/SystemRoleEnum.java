package com.zhj.enums;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/4.
 */
public enum SystemRoleEnum {
    SUPER_MANIGER(1L,null,"超级管理员"),
    ALL_MANIGER(2L,1L,"总管理员"),
    DEPARTMENT_MANIGER(3L,2L,"部门管理员"),
    DEPARTMENT_STAFF(4L,3L,"部门职员"),
    CUSTOMER(5L,4L,"客户");

    private Long roleId;
    private Long parentRoleId;
    private String roleName;

    private SystemRoleEnum(Long roleId, Long parentRoleId, String roleName) {
        this.roleId = roleId;
        this.parentRoleId = parentRoleId;
        this.roleName = roleName;
    }

    public SystemRoleEnum getByRoleId(Long roleId) {
        SystemRoleEnum[] values = values();
        for (SystemRoleEnum e : values) {
            if (e.roleId.equals(roleId)) {
                return e;
            }
        }
        return null;
    }

    public List<SystemRoleEnum> getByParentId(Long parentRoleId) {
        List<SystemRoleEnum> list = new ArrayList<SystemRoleEnum>();
        SystemRoleEnum[] values = values();
        SystemRoleEnum currentEnum = null;
        if (null != parentRoleId) {
            for (SystemRoleEnum e : values) {
                if (e.roleId.equals(parentRoleId)) {
                    list.add(e);
                    currentEnum = e;
                    break;
                }
            }
            boolean flag = false;
            while (!flag) {
                flag = false;
                for (SystemRoleEnum e : values) {
                    if (currentEnum.roleId.equals(e.parentRoleId)) {
                        list.add(e);
                        currentEnum = e;
                        flag = true;
                        break;
                    }
                }
            }
        }
        return list;
    }


    public Long getRoleId() {
        return roleId;
    }

    public Long getParentRoleId() {
        return parentRoleId;
    }

    public String getRoleName() {
        return roleName;
    }
}
