import com.xtl.mapper.PersonMapper;
import com.xtl.mapper.RoleMapper;
import com.xtl.pojo.Person;
import com.xtl.pojo.Role;
import com.xtl.util.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.PropertyConfigurator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class TestManyToMany {
    public SqlSession sqlSession;
    public RoleMapper roleMapper;
    public PersonMapper personMapper;

    @Before
    public void before() {
        //配置log4j.properties文件的位置
        PropertyConfigurator.configure("src/main/resources/conf/log4j.properties");
        sqlSession= MyBatisUtils.getSqlSession();
        roleMapper=sqlSession.getMapper(RoleMapper.class);
        personMapper=sqlSession.getMapper(PersonMapper.class);
    }

    @Test
    public void testFindAllRoles(){
        List<Role> allRoles = roleMapper.findAllRoles();
        for(Role role:allRoles){
            System.out.println(role+"\t"+role.getPersons());
        }
    }

    @Test
    public void testFindAllPersons(){
        List<Person> allPersons = personMapper.findAllPersons();
        for(Person person:allPersons){
            System.out.println(person+"\t"+person.getRoles());
        }
    }

    @After
    public void after() throws IOException {
        MyBatisUtils.close();
    }
}
