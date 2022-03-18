package com.xtl.dao.impl;

import com.xtl.dao.StudentDAO;
import com.xtl.mapper.StudentMapper;
import com.xtl.pojo.Student;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

/**
 * JDBC模板
 * @author 31925
 */
public class StudentJdbcTemplateImpl implements StudentDAO {

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplateObject;

    @Override
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplateObject = new JdbcTemplate(dataSource);
    }

    @Override
    public void addStudent(Student student) {
        String sql = "insert into student (name, age) values (?, ?)";
        jdbcTemplateObject.update(sql, student.getName(), student.getAge());
    }

    @Override
    public List<Student> listStudents() {
        String sql = "select * from student";
        return jdbcTemplateObject.query(sql, new StudentMapper());
    }

    @Override
    public Student getStudent(Integer id) {
        String sql="select * from student where id=?";
        return jdbcTemplateObject.queryForObject(sql, new StudentMapper(), id);
    }

    @Override
    public void deleteStudentById(Integer id) {
        String sql="delete from student where id=?";
        jdbcTemplateObject.update(sql,id);
    }

    @Override
    public void updateStudent(Student student) {
        String sql="update student set name=?,age=? where id=?";
        jdbcTemplateObject.update(sql,student.getName(),student.getAge(),student.getId());
    }
}
