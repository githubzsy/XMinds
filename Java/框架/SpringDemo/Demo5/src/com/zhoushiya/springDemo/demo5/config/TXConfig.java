package com.zhoushiya.springDemo.demo5.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * @author zhoushiya
 * @date 2020/9/20 16:16
 */
@Configuration
@ComponentScan(basePackages = "com.zhoushiya.springDemo.demo5")
// 开启事务配置
@EnableTransactionManagement
public class TXConfig {

    /**
     * 创建数据库连接池
     *
     * @return
     */
    @Bean
    public DruidDataSource getDruidDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost/test?serverTimezone=UTC");
        dataSource.setUsername("root");
        dataSource.setPassword("welcome");
        return dataSource;
    }

    /**
     * 创建JdbcTemplate创建
     *
     * @param dataSource 容器自动注入的DataSource对象
     * @return
     */
    @Bean
    public JdbcTemplate getJdbcTemplate(DataSource dataSource) {

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        return jdbcTemplate;
    }

    /**
     * 创建TransactionManager
     *
     * @param dataSource 容器自动注入的DataSource对象
     * @return
     */
    @Bean
    public DataSourceTransactionManager getDataSourceTransactionManager(DataSource dataSource) {
        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager(dataSource);
        return transactionManager;
    }
}
