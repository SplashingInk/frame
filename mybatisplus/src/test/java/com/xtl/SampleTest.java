package com.xtl;

import com.xtl.entity.User;
import com.xtl.mapper.UserMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName SampleTest
 * @Description TODO
 * @Author xtl
 * @Date 2022/3/10 23:44
 */
@SpringBootTest
public class SampleTest {
    @Resource
    private UserMapper userMapper;

    @Test
    public void testSelect() {
        System.out.println(("----- selectAll method test ------"));
        List<User> userList = userMapper.selectList(null);
        Assertions.assertEquals(3, userList.size());
        userList.forEach(System.out::println);
    }

    @Test
    public void testInsert(){
        User user=new User();
        user.setAge(25);
        user.setName("李星云");
        user.setEmail("test6@baomidou.com");
        int result = userMapper.insert(user);
        System.out.println("插入的结果是："+result);
        System.out.println("插入后数据的id:"+user.getId());
    }

    @Test
    public void testDelete(){
        int result = userMapper.deleteById(1);
        System.out.println("删除结果："+result);
        Map<String,Object> map=new HashMap<>(16);
        map.put("name","姬如雪");
        map.put("age",20);
        int deleteByMap = userMapper.deleteByMap(map);
        System.out.println("删除的结果："+deleteByMap);
    }


    @Test
    public void testUpdate(){
        User user=new User();
        user.setId(3L);
        user.setName("姬如雪");
        user.setAge(20);
        int result = userMapper.updateById(user);
        System.out.println("更新的结果是："+result);
    }

    @Test
    public void testSelectByIds(){
        List<Integer> list= Arrays.asList(1,3,5);
        List<User> userList = userMapper.selectBatchIds(list);
        System.out.println(userList);
        Map<String, Object> map = userMapper.selectMapById(1L);
        System.out.println(map);
    }
}
