package com.xtl;

import com.xtl.dao.impl.StudentJdbcTemplateImpl;
import com.xtl.pojo.Student;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class TestJdbc {
    public ApplicationContext applicationContext;
    public StudentJdbcTemplateImpl studentJDBCTemplate;

    @Before
    public void before(){
        System.out.println("=======测试开始==========");
        applicationContext= new ClassPathXmlApplicationContext("conf/springConfig.xml");
        studentJDBCTemplate =
                (StudentJdbcTemplateImpl) applicationContext.getBean("studentJDBCTemplate");
    }

    @Test
    public void  addStudent(){
        System.out.println("------Records Creation--------" );
        Student mary=new Student("mary",23);
        Student tom=new Student("tom",33);
        Student king=new Student("king",53);
        studentJDBCTemplate.addStudent(mary);
        studentJDBCTemplate.addStudent(tom);
        studentJDBCTemplate.addStudent(king);
        listStudent();
    }

    @Test
    public void listStudent(){
        System.out.println("------Listing Multiple Records--------" );
        List<Student> students = studentJDBCTemplate.listStudents();
        for (Student record : students) {
            System.out.print("ID : " + record.getId());
            System.out.print(", Name : " + record.getName());
            System.out.println(", Age : " + record.getAge());
        }
    }

    @Test
    public void getStudentById(){
        System.out.println(studentJDBCTemplate.getStudent(2));
    }

    @Test
    public void deleteStudentById(){
        studentJDBCTemplate.deleteStudentById(2);
        listStudent();
    }

    @Test
    public void updateStudent(){
        Student student=new Student("陆林轩",25);
        student.setId(5);
        studentJDBCTemplate.updateStudent(student);
        listStudent();
    }

    @After
    public void after(){
        System.out.println("==========测试结束=========");
    }

}
