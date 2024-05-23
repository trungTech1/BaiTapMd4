package router.service;

import router.interfaces.UserServiceI;
import router.model.User;
import router.model.UserUpdate;
import router.mysql.MySql;

import java.lang.reflect.Field;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserService implements UserServiceI {
    private static final String INSERT_USER = "INSERT INTO user (name, password,country) VALUES (?, ?, ?)"; // Changed from userName to name
    private static final String SELECT_ALL_USER = "SELECT * FROM user";
    private static final String DELETE = "DELETE FROM user WHERE id = ?";

    @Override
    public void addUser(User user) {
        try {
            System.out.println("User: " + user.getName() + " " + user.getPassword() + " " + user.getCountry()); // Changed from getUserName to getName
            PreparedStatement preparedStatement = MySql.connection.prepareStatement(INSERT_USER);
            preparedStatement.setString(1, user.getName()); // Changed from getUserName to getName
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getCountry());
            preparedStatement.executeUpdate();
            System.out.println("add user success");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUser() {
        List<User> users = new ArrayList<>();
        System.out.println("da vao day");
        try {
            PreparedStatement preparedStatement = MySql.connection.prepareStatement(SELECT_ALL_USER);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.setId(preparedStatement.getResultSet().getInt("id"));
                user.setName(preparedStatement.getResultSet().getString("name")); // Changed from setUserName to setName
                user.setPassword(preparedStatement.getResultSet().getString("password"));
                user.setCountry(preparedStatement.getResultSet().getString("country"));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return users;
    }

    @Override
    public User getUserById(Integer id) {
        try {
            PreparedStatement preparedStatement = MySql.connection.prepareStatement("SELECT * FROM user WHERE id = ?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                User user = new User();
                user.setId(preparedStatement.getResultSet().getInt("id"));
                user.setName(preparedStatement.getResultSet().getString("name")); // Changed from setUserName to setName
                user.setPassword(preparedStatement.getResultSet().getString("password"));
                user.setCountry(preparedStatement.getResultSet().getString("country"));
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean deleteUser(Integer id) {
        try {
            PreparedStatement preparedStatement = MySql.connection.prepareStatement(DELETE);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateUser(Integer userId, UserUpdate dataUser) {
        try{
            String updateQuery = "UPDATE user SET";
            String endQuery = "WHERE id = " + userId;
            for (Field field : dataUser.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                if (field.get(dataUser) != null) {
                    updateQuery += " " + field.getName() + " = '" + field.get(dataUser) + "',";
                }
            }
            String finalQuery = updateQuery.substring(0, updateQuery.length() - 1) + " " + endQuery;
            PreparedStatement preparedStatement = MySql.connection.prepareStatement(finalQuery);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException | IllegalAccessException e) {
            e.printStackTrace();
            return false;
        }
    }
    @Override
    public List<User> getUserByCountry(String keyword) {
        List<User> users = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = MySql.connection.prepareStatement("SELECT * FROM user WHERE LOWER(country) LIKE LOWER(?)");
            preparedStatement.setString(1, "%" + keyword + "%");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.setId(preparedStatement.getResultSet().getInt("id"));
                user.setName(preparedStatement.getResultSet().getString("name")); // Changed from setUserName to setName
                user.setPassword(preparedStatement.getResultSet().getString("password"));
                user.setCountry(preparedStatement.getResultSet().getString("country"));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return users;
    }
    @Override
    public List<User> sortBy(String keyword) {
        List<User> users = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = MySql.connection.prepareStatement("SELECT * FROM user ORDER BY " + keyword);
            System.out.println("SELECT * FROM user ORDER BY " + keyword);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setPassword(resultSet.getString("password"));
                user.setCountry(resultSet.getString("country"));
                users.add(user);
                System.out.println(user.toString());
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return users;
    }
}