package com.xtl.dao;

import com.xtl.pojo.Doctor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

/**
 * @author 31925
 */
@Service
public class DoctorDaoImpl implements DoctorDao{
    @Resource
    private DataSource dataSource;

    @Resource
    private JdbcTemplate jdbcTemplate;

    public DoctorDaoImpl(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate =new JdbcTemplate(dataSource);
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Doctor> findAll() {
        String sql="select * from doctor";
        return jdbcTemplate.query(sql, new DoctorMapper());
    }

    @Override
    public Doctor getDoctorById(Integer id) {
        String sql="select * from doctor where id=?";
        return jdbcTemplate.queryForObject(sql, new DoctorMapper(), id);
    }

    @Override
    public void addDoctor(Doctor doctor) {
        String sql="insert into doctor(id,username,age,date) value(?,?,?,?)";
        jdbcTemplate.update(sql,doctor.getId(),doctor.getUsername(),doctor.getAge(),doctor.getDate());
    }

    @Override
    public void updateDoctor(Doctor doctor) {
        String sql="update doctor set username=?,age=?,date=? where id=?";
        jdbcTemplate.update(sql,doctor.getUsername(),doctor.getAge(),doctor.getDate(),doctor.getId());
    }

    @Override
    public void deleteDoctorById(Integer id) {
        String sql="delete from doctor where id=?";
        jdbcTemplate.update(sql,id);
    }
}
