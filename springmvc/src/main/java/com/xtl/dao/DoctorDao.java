package com.xtl.dao;

import com.xtl.pojo.Doctor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 31925
 */
@Repository
public interface DoctorDao {

    /**
     * 返回医生列表
     * @return 医生列表
     */
    List<Doctor> findAll();

    /**
     * 根据id查询医生
     * @param id 医生id
     * @return  医生
     */
    Doctor getDoctorById(Integer id);

    /**
     * 添加医生
     * @param doctor 医生
     */
    void addDoctor(Doctor doctor);

    /**
     * 修改医生信息
     * @param doctor 需要修改的医生信息
     */
    void updateDoctor(Doctor doctor);

    /**
     * 根据id删除医生
     * @param id 医生id
     */
    void deleteDoctorById(Integer id);
}
