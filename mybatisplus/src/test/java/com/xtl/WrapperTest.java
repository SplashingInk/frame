package com.xtl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.xtl.entity.User;
import com.xtl.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

/**
 * @ClassName WrapperTest
 * @Description TODO
 * @Author xtl
 * @Date 2022/3/11 11:24
 */
@SpringBootTest
public class WrapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void test(){
        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        queryWrapper.like("name",'o')
                .between("age",10,30)
                .isNotNull("email");
        List<User> userList = userMapper.selectList(queryWrapper);
        userList.forEach(System.out::println);
    }


    @Test
    public void test2(){
        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        queryWrapper.orderByAsc("age")
                .orderByDesc("id");
        List<User> userList = userMapper.selectList(queryWrapper);
        userList.forEach(System.out::println);
    }

    @Test
    public void test3(){
        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        queryWrapper.select("name","age");
        List<Map<String, Object>> maps = userMapper.selectMaps(queryWrapper);
        maps.forEach(System.out::println);
    }

    @Test
    public void test4(){
        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        queryWrapper.inSql("id","select id from user where id<20");
        List<User> userList = userMapper.selectList(queryWrapper);
        userList.forEach(System.out::println);
    }


    @Test
    public void test5(){
       UpdateWrapper<User> updateWrapper=new UpdateWrapper<>();
       updateWrapper.like("name","o")
               .and(i->i.gt("age",10).or().isNotNull("email"));
       updateWrapper.set("name","king").set("email","xlt@test.comm");
        int update = userMapper.update(null, updateWrapper);
        System.out.println(update);
    }

    @Test
    public void testLambdaUpdateWrapper(){
        LambdaUpdateWrapper<User> lambdaQueryWrapper=new LambdaUpdateWrapper<>();
        lambdaQueryWrapper.like(User::getName,"o")
                .and(i->i.gt(User::getAge,10).or().isNotNull(User::getEmail));
        lambdaQueryWrapper.set(User::getName,"king").set(User::getEmail,"xlt@test.comm");
        int update = userMapper.update(null, lambdaQueryWrapper);
        System.out.println(update);
    }

    @Test
    public void test6(){
        String name="";
        Integer beginAge=10;
        Integer endAge=30;
        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        if(StringUtils.isNotBlank(name)){
            queryWrapper.like("name",name);
        }
        if(ObjectUtils.isNotNull(beginAge)){
            queryWrapper.ge("age",beginAge);
        }
        if(ObjectUtils.isNotNull(endAge)){
            queryWrapper.le("age",endAge);
        }
        List<User> userList = userMapper.selectList(queryWrapper);
        userList.forEach(System.out::println);
    }

    @Test
    public void test7(){
        String name="";
        Integer beginAge=10;
        Integer endAge=30;
        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank(name),"name",name)
                .ge(ObjectUtils.isNotNull(beginAge),"age",beginAge)
                .le(ObjectUtils.isNotNull(endAge),"age",endAge);
        List<User> userList = userMapper.selectList(queryWrapper);
        userList.forEach(System.out::println);
    }

    @Test
    public void testLambdaQueryWrapper(){
        String name="";
        Integer beginAge=10;
        Integer endAge=30;
        LambdaQueryWrapper<User> lambdaQueryWrapper=new LambdaQueryWrapper<>();
        lambdaQueryWrapper.like(StringUtils.isNotBlank(name),User::getName,name)
                .ge(ObjectUtils.isNotNull(beginAge),User::getAge,beginAge)
                .le(ObjectUtils.isNotNull(endAge),User::getAge,endAge);
        List<User> userList = userMapper.selectList(lambdaQueryWrapper);
        userList.forEach(System.out::println);
    }




}
