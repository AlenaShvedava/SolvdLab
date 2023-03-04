package pl.solvd.concerthall.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.Reader;

public class MyBatisFactory {
    private final static Logger LOG = LogManager.getLogger(MyBatisFactory.class);
    private final static MyBatisFactory myBatisFactory = new MyBatisFactory();
    private static SqlSessionFactory sqlSessionFactory;
    private MyBatisFactory() {
        try {
            Reader reader = Resources.getResourceAsReader("mybatis_config.xml");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        } catch (IOException e) {
            LOG.error("Exception while reading configuration", e);
        }
    }
    public static SqlSessionFactory getSqlSessionFactory() {
        return sqlSessionFactory;
    }
}
