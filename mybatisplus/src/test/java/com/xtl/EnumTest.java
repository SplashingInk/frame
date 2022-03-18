package com.xtl;

import com.xtl.entity.Doctor;
import com.xtl.enumq.SexEnum;
import com.xtl.mapper.DoctorMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;

/**
 * @ClassName EnumTest
 * @Description TODO
 * @Author xtl
 * @Date 2022/3/11 13:54
 */
@SpringBootTest
public class EnumTest {
    @Autowired
    private DoctorMapper doctorMapper;

    @Test
    public void test(){
        Doctor doctor=new Doctor();
        doctor.setName("王五");
        doctor.setAge(25);
        doctor.setEmail("abc@test.com");
        doctor.setSex(SexEnum.MALE);
        int insert = doctorMapper.insert(doctor);
        System.out.println(insert);
    }
}
