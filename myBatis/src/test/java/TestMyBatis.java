import com.xtl.mapper.UserMapper;
import com.xtl.pojo.User;
import com.xtl.util.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.PropertyConfigurator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TestMyBatis {
    public SqlSession sqlSession=null;
    public UserMapper userMapper=null;

    @Before
    public void before() {
        //配置log4j.properties文件的位置
        PropertyConfigurator.configure("src/main/resources/conf/log4j.properties");
        sqlSession= MyBatisUtils.getSqlSession();
        userMapper=sqlSession.getMapper(UserMapper.class);
    }

    @Test
    public void testInsertUser(){
        User user=new User("李星云",25,'男');
        System.out.println("插入前的用户数据是："+user);
        userMapper.insertUser(user);
        //提交事务
        sqlSession.commit();
        System.out.println("插入后的用户数据是："+user);
    }

    @Test
    public void testBatchInsert(){
        List<User> list=new ArrayList<>();
        User mike=new User("mike",23,'男');
        User july=new User("july",19,'女');
        User peter=new User("peter",23,'男');
        User tom=new User("tom",18,'男');
        list.add(mike);
        list.add(july);
        list.add(peter);
        list.add(tom);
        int result= userMapper.batchInsert(list);
        sqlSession.commit();
        System.out.println("新增用户的记录数是："+result);
    }

    @Test
    public void testGetUserById(){
        User user = userMapper.getUserById(9);
        System.out.println(user);
    }

    @Test
    public void testUpdateUser(){
        User user=new User();
        user.setId(8);
        user.setName("李茂贞");
        user.setAge(26);
        user.setSex('女');
        userMapper.updateUser(user);
        sqlSession.commit();
    }

    @Test
    public void testFindAll(){
        List<User> users = userMapper.findAll();
        System.out.println(users);
    }

    @Test
    public void testFindUserLike(){
        List<User> users = userMapper.findUserLike("徐");
        System.out.println(users);
    }

    @Test
    public void testSelectUserByKeyWord(){
        List<User> users = userMapper.selectUserByKeyWord("徐");
        System.out.println(users);
    }

    @Test
    public void testSelectUserByKeyWord2(){
        List<User> users = userMapper.selectUserByKeyWord2("徐");
        System.out.println(users);
    }

    @Test
    public void testBatchSelect(){
        List<Integer> ids=new ArrayList<>();
        ids.add(7);
        ids.add(16);
        List<User> users = userMapper.batchSelect(ids);
        System.out.println(users);
    }

    @Test
    public void testDeleteUserById(){
        userMapper.deleteUserById(1);
        testFindAll();
    }

    @Test
    public void testBatchDelete(){
        List<Integer> ids=new ArrayList<>();
        ids.add(6);
        ids.add(15);
        int result= userMapper.batchDelete(ids);
        sqlSession.commit();
        System.out.println("删除的用户数量是："+result);
    }

    @Test
    public void testBatchUpdate(){
        List<User> list=new ArrayList<>();
        User king=new User(7,"king",27,'男');
        User queen=new User(8,"queen",30,'女');
        list.add(king);
        list.add(queen);
        int result=userMapper.batchUpdate(list);
        sqlSession.commit();
        System.out.println("更新的用户数量是："+result);
    }

    @After
    public void after() throws IOException {
        MyBatisUtils.close();
    }
}
