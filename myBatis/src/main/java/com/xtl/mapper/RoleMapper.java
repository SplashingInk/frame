package com.xtl.mapper;

import com.xtl.pojo.Role;

import java.util.List;

/**
 * 角色实体类接口
 * @author 31925
 */
public interface RoleMapper {
    /**
     * 查找所有角色
     * @return 返回所有角色集合
     */
    List<Role> findAllRoles();
}
