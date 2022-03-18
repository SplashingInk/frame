package com.xtl;

import com.xtl.entity.User;
import com.xtl.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName ServiceTest
 * @Description TODO
 * @Author xtl
 * @Date 2022/3/11 10:35
 */
@SpringBootTest
public class ServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void test(){
        int count = userService.count();
        System.out.println("总记录数："+count);
    }

    @Test
    public void testInsertBatch(){
        User user=new User();
        user.setName("袁天罡");
        user.setAge(100);
        user.setEmail("test@baomidou.com");
        boolean b = userService.save(user);
        System.out.println("添加的结果是："+b);
        List<User> userList=new ArrayList<>();
        for(int i=0;i<10;i++){
            User temp=new User();
            temp.setName("joker"+i);
            temp.setAge(25+i);
            temp.setEmail("test@baomidou.com");
            userList.add(temp);
        }
        boolean saveBatch = userService.saveBatch(userList);
        System.out.println("添加的结果是："+saveBatch);
    }
}
