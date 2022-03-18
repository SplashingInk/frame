import com.xtl.mapper.CardMapper;
import com.xtl.mapper.NurseMapper;
import com.xtl.pojo.Card;
import com.xtl.pojo.Nurse;
import com.xtl.pojo.NurseExt;
import com.xtl.util.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.PropertyConfigurator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class TestOneToOne {
    public SqlSession sqlSession;
    public NurseMapper nurseMapper;

    @Before
    public void before() {
        //配置log4j.properties文件的位置
        PropertyConfigurator.configure("src/main/resources/conf/log4j.properties");
        sqlSession= MyBatisUtils.getSqlSession();
        nurseMapper=sqlSession.getMapper(NurseMapper.class);
    }
    
    @Test
    public void testQueryNurseExtById(){
        NurseExt nurseExt = nurseMapper.queryNurseExtById(1);
        System.out.println(nurseExt);
    }

    @Test
    public void testQueryNurseById(){
        Nurse nurse = nurseMapper.queryNurseById(1);
        System.out.println(nurse);
    }

    @Test
    public void testQueryNurseById2(){
        Nurse nurse = nurseMapper.queryNurseById2(1);
        System.out.println(nurse);
    }

    @Test
    public void testQueryCardByNo(){
        CardMapper cardMapper=sqlSession.getMapper(CardMapper.class);
        Card card = cardMapper.queryCardByNo(1);
        System.out.println(card);
    }

    @After
    public void after() throws IOException {
        MyBatisUtils.close();
    }
}
