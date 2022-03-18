package com.xtl.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xtl.entity.User;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * @author 31925
 */
@Repository
public interface UserMapper extends BaseMapper<User> {
    /**
     * 根据id查询
     * @param id id
     * @return  map集合
     */
    @MapKey("id")
    Map<String,Object> selectMapById( Long id);

    /**
     * 按照年龄分页查询
     * @param page 分页对象，必须位于第一个参数位置
     * @param age 年龄
     * @return 年龄分页
     */
    Page<User> selectPageByAge(@Param("page") Page<User> page,@Param("age") Integer age);
}
