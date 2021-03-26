package kr.ac.jejunu;

import java.sql.*;

public class UserDao {
    public User get(Integer id) throws ClassNotFoundException, SQLException {

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection =
                DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/portal?" +
                                "characterEncoding=utf-8&serverTimezone=UTC"
                        ,"root","7981");
        //쿼리만들
        PreparedStatement preparedStatement = connection.prepareStatement(
                "select * from userinfo where id = ?"
        );
        preparedStatement.setInt(1, id);
        //쿼리실행하고
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        //데이터를 오브젝트  매핑
        User user = new User();
        user.setId(resultSet.getInt("id"));
        user.setName(resultSet.getString("name"));
        user.setPassword(resultSet.getString("password"));
        //자원해제
        resultSet.close();
        preparedStatement.close();
        connection.close();

        return user;
    }

}
