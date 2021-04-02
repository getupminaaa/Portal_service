package kr.ac.jejunu;

import javax.sql.DataSource;
import java.sql.*;

public class UserDao {

    private final DataSource dataSource;

    public UserDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    public User findById(Integer id) throws SQLException {

        Connection connection = null;  //변하는 것 = Strategy
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        User user = null;
        try {
            connection = dataSource.getConnection();
            //쿼리만들기
            preparedStatement = connection.prepareStatement( //변하지 않는 것 = Context
                    "select * from userinfo where id = ?"
            );
            preparedStatement.setInt(1, id);
            //쿼리실행하고
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                //데이터를 오브젝트  매핑
                user = new User();
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setPassword(resultSet.getString("password"));
            }
        } finally {
            try {
                resultSet.close();
            } catch (Exception throwables) {
                throwables.printStackTrace();
            }
            try {
                preparedStatement.close();
            } catch (Exception throwables) {
                throwables.printStackTrace();
            }
            try {
                connection.close();
            } catch (Exception throwables) {
                throwables.printStackTrace();
            }
        }
        //자원해제
        return user;
    }


    public void insert(User user) throws SQLException, ClassNotFoundException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet;
        try {
            connection = dataSource.getConnection();
            //쿼리만들고
            preparedStatement = connection.prepareStatement(
                    "insert into userinfo(name, password) values (?, ?)", Statement.RETURN_GENERATED_KEYS
            );
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getPassword());
            //쿼리실행하고
            preparedStatement.executeUpdate();
            //데이터를 오브젝트  매핑
            resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();
            //자원해제
            user.setId(resultSet.getInt(1));
        } finally {
            try {
                preparedStatement.close();
            } catch (Exception throwables) {
                throwables.printStackTrace();
            }
            try {
                connection.close();
            } catch (Exception throwables) {
                throwables.printStackTrace();
            }

        }
    }

    public void update(User user) throws SQLException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet;
        try {
            connection = dataSource.getConnection();
            //쿼리만들고
            preparedStatement = connection.prepareStatement(
                    "update userinfo set name = ?, password = ? where id = ?"

            );
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setInt(3, user.getId());

            //쿼리실행하고
            preparedStatement.executeUpdate();

        } finally {
            try {
                preparedStatement.close();
            } catch (Exception throwables) {
                throwables.printStackTrace();
            }
            try {
                connection.close();
            } catch (Exception throwables) {
                throwables.printStackTrace();
            }

        }
    }

    public void delete(Integer id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet;
        try {
            connection = dataSource.getConnection();
            //쿼리만들고
            preparedStatement = connection.prepareStatement(
                    "delete from userinfo where id = ?"

            );
            preparedStatement.setInt(1, id);

            //쿼리실행하고
            preparedStatement.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                preparedStatement.close();
            } catch (Exception throwables) {
                throwables.printStackTrace();
            }
            try {
                connection.close();
            } catch (Exception throwables) {
                throwables.printStackTrace();
            }

        }

    }

//   public Connection getConnection() throws ClassNotFoundException, SQLException{
//       return connectionMaker.getConnection();
//   }
}
