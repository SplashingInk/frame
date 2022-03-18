package com.xtl.mapper;

import com.xtl.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 31925
 * 用户接口
 */
public interface UserMapper {

    /**
     * 添加用户
     * @param user 添加的用户
     */
    void insertUser(User user);


    /**
     * 根据id查询用户
     * @param id 用户id
     * @return 用户
     */
    User getUserById(Integer id);

    /**
     * 查找所有的用户
     * @return 所有用户的集合
     */
    List<User> findAll();


    /**
     * 更新用户信息
     * @param user 需要更新的用户
     */
    void updateUser(User user);

    /**
     * 根据id来删除用户
     * @param id 要删除的用户的id
     */
    void deleteUserById(Integer id);


    /**
     * 按照关键字模糊查询
     * @param name 模糊查询的字段
     * @return  结果集
     */
    List<User> findUserLike(String name);

    /**
     * 按照关键字模糊查询
     * @param keyword 模糊查询的字段
     * @return 结果集
     */
    List<User> selectUserByKeyWord(String keyword);

    /**
     * 按照关键字模糊查询
     * @param keyword 模糊查询的字段
     * @return 结果集
     */
    List<User> selectUserByKeyWord2(String keyword);


    /**
     * 批量插入用户
     * @param list 待插入的用户集合
     * @return 插入的用户数量
     */
    int batchInsert(@Param("list")List<User> list);


    /**
     * 批量删除用户
     * @param ids 需要删除的用户的id集合
     * @return 删除的用户数量
     */
    int batchDelete(@Param("ids") List<Integer> ids);

    /**
     * 批量根据id查询用户
     * @param ids 批量查询的用户的id集合
     * @return 用户集合
     */
    List<User> batchSelect(@Param("ids") List<Integer> ids);

    /**
     * 批量更新用户
     * @param list 需要更新的用户集合
     * @return 更新的用户数量
     */
    int batchUpdate(List<User> list);
}
