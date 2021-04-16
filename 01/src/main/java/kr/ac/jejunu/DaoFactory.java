package kr.ac.jejunu;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import javax.sql.DataSource;
import java.sql.Driver;

@Configuration
public class DaoFactory {
    @Value("${db.driver}")
    private String className;
    @Value("${db.username}")
    private String username;
    @Value("${db.password}")
    private String password;
    @Value("${db.url}")
    private String url;

/*
    @Bean
    public UserDao userDao() throws ClassNotFoundException {
        return new UserDao(jdbcTemplate());
    }
*/

    @Bean
    public JdbcTemplate jdbcTemplate() throws ClassNotFoundException {
        return new JdbcTemplate(dataSource());
    }


    @Bean
    public DataSource dataSource() throws ClassNotFoundException {
        SimpleDriverDataSource dataSource
                = new SimpleDriverDataSource();

//        className = "com.mysql.cj.jdbc.Driver";
//        username = "root";
//        password = "7981";
//        url = "jdbc:mysql://localhost:3306/portal?characterEncoding=utf-8&serverTimezone=UTC";

        dataSource.setDriverClass((Class<? extends Driver>) Class.forName(className)); //왜 Class.forname 만 써야 응답할까? -> mysql driver은 runtimeonly로 지정 - 실행 시에 로딩이 필요함
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setUrl(url);

        return dataSource;
    }
}

