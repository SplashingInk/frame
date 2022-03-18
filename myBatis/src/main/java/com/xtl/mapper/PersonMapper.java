package com.xtl.mapper;

import com.xtl.pojo.Person;

import java.util.List;

/**
 * 个人接口类
 * @author 31925
 */
public interface PersonMapper {
    /**
     * 查找所有用户
     * @return 所有用户
     */
    List<Person> findAllPersons();
}
