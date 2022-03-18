package com.xtl.service;

import com.xtl.dao.DoctorDao;
import com.xtl.pojo.Doctor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 31925
 */
@Service
public class DoctorServiceImpl implements DoctorService{
    @Resource
    private DoctorDao doctorDao;


    @Override
    public List<Doctor> findAll() {
        return doctorDao.findAll();
    }

    @Override
    public Doctor getDoctorById(Integer id) {
        return doctorDao.getDoctorById(id);
    }

    @Override
    public void addDoctor(Doctor doctor) {
        doctorDao.addDoctor(doctor);
    }

    @Override
    public void updateDoctor(Doctor doctor) {
        doctorDao.updateDoctor(doctor);
    }


    @Override
    public void deleteDoctorById(Integer id) {
        doctorDao.deleteDoctorById(id);
    }
}
