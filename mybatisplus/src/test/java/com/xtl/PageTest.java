package com.xtl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xtl.entity.Doctor;
import com.xtl.entity.User;
import com.xtl.mapper.DoctorMapper;
import com.xtl.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @ClassName PageTest
 * @Description TODO
 * @Author xtl
 * @Date 2022/3/11 12:53
 */
@SpringBootTest
public class PageTest {

    @Autowired
    private UserMapper userMapper;
    
    @Autowired
    private DoctorMapper doctorMapper;

    @Test
    public void test(){
        Page<User> page=new Page<>(1,2);
        userMapper.selectPage(page,null);
        System.out.println(page.getPages());
    }

    @Test
    public void test2(){
        Page<User> page=new Page<>(1,2);
        userMapper.selectPageByAge(page,10);
        System.out.println(page.getPages());
    }
    
    @Test
    public void test3(){
        Doctor doctorLi = doctorMapper.selectById(1);
        System.out.println("小李查询到的年龄："+doctorLi.getAge());
        Doctor doctorWang = doctorMapper.selectById(1);
        System.out.println("小李查询到的年龄："+doctorWang.getAge());
        doctorLi.setAge(doctorLi.getAge()+2);
        doctorMapper.updateById(doctorLi);
        doctorWang.setAge(doctorWang.getAge()+10);
        int result = doctorMapper.updateById(doctorWang);
        if(result==0){
            Doctor doctor = doctorMapper.selectById(1);
            doctor.setAge(doctor.getAge()+10);
            doctorMapper.updateById(doctor);
        }
        Doctor doctorBoss = doctorMapper.selectById(1);
        System.out.println("老板查询到的年龄："+doctorBoss.getAge());
    }

}
