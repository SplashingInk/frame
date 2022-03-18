package com.xtl.dao;

import com.xtl.pojo.Student;

import javax.sql.DataSource;
import java.util.List;

/**
 * @author 31925
 */
public interface StudentDAO {
    /**
     * 设置数据源
     * @param ds 数据源
     */
    void setDataSource(DataSource ds);


    /**
     * 添加学生
     * @param student 学生
     */
    void addStudent(Student student);

    /**
     * 根据id查询学生
     * @param id 学生id
     * @return 学生
     */
    Student getStudent(Integer id);

    /**
     * 查询所有学生
     * @return 学生列表
     */
    List<Student> listStudents();

    /**
     * 根据id删除学生
     * @param id 学生id
     */
    void deleteStudentById(Integer id);


    /**
     * 更新学生信息
     * @param student 学生
     */
    void updateStudent(Student student);
}
