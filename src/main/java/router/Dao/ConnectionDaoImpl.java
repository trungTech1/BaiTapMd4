package router.Dao;

import router.model.User;
import router.mysql.MySql;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ConnectionDaoImpl implements ConnectionDaoI {

    @Override
    public List<User> sortBy(String keyword) {
        List<User> users = new ArrayList<>();
        // Mở kết nối
        Connection connection = MySql.getConnection();
        // Thực hiện câu lệnh truy vấn
        try {
            CallableStatement callData = connection.prepareCall("select * from user order by name");
            ResultSet resultSet = callData.executeQuery();
            while (resultSet.next()){
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setPassword(resultSet.getString("password"));
                user.setCountry(resultSet.getString("country"));
                users.add(user);
            }
            return users;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            MySql.closeConnection(connection);
        }
        return null;
    }

    @Override
    public List<User> getUserByCountry(String keyword) {
        List<User> users = new ArrayList<>();
        // Mở kết nối
        Connection connection = MySql.getConnection();
        // Thực hiện câu lệnh truy vấn
        try {
            CallableStatement callData = connection.prepareCall("select * from user where country = ?");
            callData.setString(1, keyword);
            ResultSet resultSet = callData.executeQuery();
            while (resultSet.next()){
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setPassword(resultSet.getString("password"));
                user.setCountry(resultSet.getString("country"));
                users.add(user);
            }
            return users;
        }catch (Exception e){
            e.printStackTrace();
        } finally {
            MySql.closeConnection(connection);
        }
        return null;
    }

    @Override
    public User getUserById(Integer id) {
        // Mở kết nối
        Connection connection = MySql.getConnection();
        // Thực hiện câu lệnh truy vấn
        try {
            CallableStatement callData = connection.prepareCall("select * from user where id = ?");
            callData.setInt(1, id);
            ResultSet resultSet = callData.executeQuery();
            if (resultSet.next()){
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setPassword(resultSet.getString("password"));
                user.setCountry(resultSet.getString("country"));
                return user;
            }
        }catch (Exception e){
            e.printStackTrace();
        } finally {
            MySql.closeConnection(connection);
        }
        return null;
    }

    @Override
    public void addUser(User user) {
        // Mở kết nối
        Connection connection = MySql.getConnection();
        // Thực hiện câu lệnh truy vấn
        try {
           if(user.getId() == null){
                CallableStatement callData = connection.prepareCall("insert into user(name, password, country, image) values(?, ?, ?, ?)");
                callData.setString(1, user.getName());
                callData.setString(2, user.getPassword());
                callData.setString(3, user.getCountry());
                callData.setString(4, user.getImage());
                callData.executeUpdate();
           }else {
                CallableStatement callData = connection.prepareCall("update user set name = ?, password = ?, country = ?, image = ? where id = ?");
                callData.setString(1, user.getName());
                callData.setString(2, user.getPassword());
                callData.setString(3, user.getCountry());
                callData.setString(4, user.getImage());
                callData.setInt(4, user.getId());
                callData.executeUpdate();
           }
        }catch (Exception e){
            e.printStackTrace();
        } finally {
            MySql.closeConnection(connection);
        }
    }

    @Override
    public List<User> getAllUser() {
        List<User> users = new ArrayList<>();
        // Mở kết nối
        Connection connection = MySql.getConnection();
        // Thực hiện câu lệnh truy vấn
        try {
            CallableStatement callData = connection.prepareCall("select * from user");
            ResultSet resultSet = callData.executeQuery();
            while (resultSet.next()){
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setPassword(resultSet.getString("password"));
                user.setCountry(resultSet.getString("country"));
                users.add(user);
            }
            return users;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void deleteUser(Integer id) {
        // Mở kết nối
        Connection connection = MySql.getConnection();
        // Thực hiện câu lệnh truy vấn
        try {
            CallableStatement callData = connection.prepareCall("delete from user where id = ?");
            callData.setInt(1, id);
            callData.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        } finally {
            MySql.closeConnection(connection);
        }
    }
}
