package kr.ac.jejunu;

import java.sql.*;

public  class UserDao {

    private final ConnectionMaker connectionMaker;

    public UserDao(ConnectionMaker connectionMaker) {
        this.connectionMaker = connectionMaker;
    }


    public User findById(Integer id) throws ClassNotFoundException, SQLException {

        Connection connection = connectionMaker.getConnection();  //변하는 것 = Strategy
        //쿼리만들기
        PreparedStatement preparedStatement = connection.prepareStatement( //변하지 않는 것 = Context
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


    public void insert(User user) throws SQLException, ClassNotFoundException {

        Connection connection = connectionMaker.getConnection();
        //쿼리만들
        PreparedStatement preparedStatement = connection.prepareStatement(
                "insert into userinfo(name, password) values (?, ?)",Statement.RETURN_GENERATED_KEYS
        );

        preparedStatement.setString(1,user.getName());
        preparedStatement.setString(2,user.getPassword());
        //쿼리실행하고

        preparedStatement.executeUpdate();
        //데이터를 오브젝트  매핑

        ResultSet resultSet = preparedStatement.getGeneratedKeys();
        resultSet.next();

        //자원해제
        user.setId(resultSet.getInt(1));

        resultSet.close();
        preparedStatement.close();
        connection.close();

    }

//   public Connection getConnection() throws ClassNotFoundException, SQLException{
//       return connectionMaker.getConnection();
//   }
}
