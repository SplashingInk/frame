import com.xtl.mapper.CountryMapper;
import com.xtl.pojo.Country;
import com.xtl.util.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.PropertyConfigurator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class TestOneToMany {
    public SqlSession sqlSession;
    public CountryMapper countryMapper;

    @Before
    public void before() {
        //配置log4j.properties文件的位置
        PropertyConfigurator.configure("src/main/resources/conf/log4j.properties");
        sqlSession= MyBatisUtils.getSqlSession();
        countryMapper=sqlSession.getMapper(CountryMapper.class);
    }

    @Test
    public void testQueryCountryById(){
        Country country = countryMapper.queryCountryById(1);
        System.out.println(country);
    }

    @Test
    public void testQueryCountryById2(){
        Country country = countryMapper.queryCountryById2(2);
        System.out.println(country);
    }

    @After
    public void after() throws IOException {
        MyBatisUtils.close();
    }
}
