import com.mybatis.mapper.UserMapper;
import com.mybatis.model.User;
import com.mybatis.session.SqlSessionFactory;
import com.mybatis.session.Sqlsession;

/**
 * @Author: WangJian
 * @Date: 2019/3/24 9:49
 */
public class TestMybatis {

    public static void main(String[] args) {
        //实例化SqlsessionFactory，加载数据库配置文件以及mapper.xml
        SqlSessionFactory factory =new SqlSessionFactory();
        Sqlsession sqlsession = factory.openSession();
//        Object o = sqlsession.selectOne("com.mybatis.mapper.UserMapper.select", 2);
//        System.out.println(sqlsession);
        UserMapper mapper = sqlsession.getMapper(UserMapper.class);
        User select = mapper.select();
        System.out.println(select);
//        sqlsession.selectOne("",1);
    }
}
