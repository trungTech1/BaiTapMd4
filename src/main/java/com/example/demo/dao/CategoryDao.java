package com.example.demo.dao;

import com.example.demo.interfaces.ICategoryDao;
import com.example.demo.model.Category;
import com.example.demo.utill.ConnectDb;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryDao implements ICategoryDao {
    @Override
    public List<Category> getAllCategories() {
        Connection connection = ConnectDb.getConnection();
        List<Category> categories = new ArrayList<>();
        try {
            CallableStatement callableStatement = connection.prepareCall("SELECT * FROM categories");
            ResultSet resultSet = callableStatement.executeQuery();
            while (resultSet.next()) {
                Category category = new Category();
                category.setId(resultSet.getInt("id"));
                category.setName(resultSet.getString("name"));
                category.setStatus(resultSet.getBoolean("status"));
                categories.add(category);
                System.out.println(category.toString());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return categories;
    }

    @Override
    public Category getCategoryById(Integer id) {
      Connection connection = ConnectDb.getConnection();
        try {
            CallableStatement callableStatement = connection.prepareCall("SELECT * FROM categories WHERE id = ?");
            callableStatement.setInt(1, id);
            ResultSet resultSet = callableStatement.executeQuery();
            while (resultSet.next()) {
                Category category = new Category();
                category.setId(resultSet.getInt("id"));
                category.setName(resultSet.getString("name"));
                category.setStatus(resultSet.getBoolean("status"));
                return category;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public void saveCategory(Category category) {
        Connection connection = ConnectDb.getConnection();
        if(category.getId() != null){
            try {
                CallableStatement callableStatement = connection.prepareCall("UPDATE categories SET name = ?, status = ? WHERE id = ?");
                callableStatement.setString(1, category.getName());
                callableStatement.setBoolean(2, category.getStatus());
                callableStatement.setInt(3, category.getId());
                callableStatement.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else {
            try {
                CallableStatement callableStatement = connection.prepareCall("INSERT INTO categories(name, status) VALUES (?, ?)");
                callableStatement.setString(1, category.getName());
                callableStatement.setBoolean(2, category.getStatus());
                callableStatement.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public Boolean deleteCategory(Integer id) {
        Connection connection = ConnectDb.getConnection();
        try {
            // Kiểm tra xem có bất kỳ bản ghi nào trong bảng books đang tham chiếu đến category này không
            String checkQuery = "SELECT COUNT(*) FROM books WHERE category_id = ?";
            PreparedStatement checkStmt = connection.prepareStatement(checkQuery);
            checkStmt.setInt(1, id);
            ResultSet rs = checkStmt.executeQuery();
            rs.next();
            int count = rs.getInt(1);

            if (count > 0) {
                // Có bản ghi trong bảng books đang tham chiếu đến category này, không thể xóa
                return false;
            } else {
                // Không có bản ghi nào trong bảng books tham chiếu đến category này, tiến hành xóa
                CallableStatement callableStatement = connection.prepareCall("DELETE FROM categories WHERE id = ?");
                callableStatement.setInt(1, id);
                callableStatement.executeUpdate();
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            if(connection != null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
