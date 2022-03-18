package com.xtl.dao;

import com.xtl.pojo.Doctor;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * @author 31925
 */
public class DoctorMapper implements RowMapper<Doctor> {

    @Override
    public Doctor mapRow(ResultSet resultSet, int i) throws SQLException {
        Doctor doctor=new Doctor();
        doctor.setId(resultSet.getInt("id"));
        doctor.setUsername(resultSet.getString("username"));
        doctor.setAge(resultSet.getInt("age"));
        doctor.setDate(resultSet.getDate("date"));
        return doctor;
    }
}
