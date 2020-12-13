package learning;

import com.github.pagehelper.PageInterceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import tk.mybatis.spring.mapper.MapperScannerConfigurer;

import javax.sql.DataSource;
import java.util.Properties;

//专用的测试配置类
@Configuration
@ComponentScan("com.yc.learning.**")
@EnableTransactionManagement
public class DaoConfiguration {

    @Bean   //事件源
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://39.105.183.155:3306/yc_learning?useUnicode=true&characterEncoding=utf8&useSSL=false&serverTimezone=Hongkong\n");
        dataSource.setUsername("root");
        dataSource.setPassword("Ly1982119376.");
        return dataSource;
    }

    @Bean   //联接工厂
    public SqlSessionFactory sqlSessionFactoryBean(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        PageInterceptor pg =new PageInterceptor();
        bean.setDataSource(dataSource);
        bean.setPlugins(pg);
        return bean.getObject();
    }

    @Bean(name = "transactionManager")   //事务管理器
    public PlatformTransactionManager createTransactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Configuration
    @AutoConfigureAfter(DaoConfiguration.class) //有顺序
    public static class MyBatisMapperScannerConfig {

        @Bean
        public MapperScannerConfigurer mapperScannerConfig() {
            MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
            mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactoryBean");  //创建mapperbean时，要注入  sqlsessionfactorybean
            //扫描   针对每个表的mapper位置
            mapperScannerConfigurer.setBasePackage("com.yc.learning.dao.impl");
            //配置通用mappers
            Properties properties = new Properties();
            //   指定通用mapper的位置，一定不能在   com.yc.piclib.dao.impl
            properties.setProperty("mappers", "com.yc.learning.dao.MisBaseMapper");
            properties.setProperty("notEmpty", "false");
            properties.setProperty("IDENTITY", "MYSQL");        //注意数据库的主键类型
            mapperScannerConfigurer.setProperties(properties);

            return mapperScannerConfigurer;
        }

    }

}
