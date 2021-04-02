package kr.ac.jejunu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public interface DataSource {

    public Connection getConnection() throws ClassNotFoundException, SQLException;
//        Class.forName("com.mysql.cj.jdbc.Driver");
//        return DriverManager.getConnection(
//                "jdbc:mysql://localhost:3306/portal?" +
//                        "characterEncoding=utf-8&serverTimezone=UTC"
//                , "root", "7981"
//        );

}