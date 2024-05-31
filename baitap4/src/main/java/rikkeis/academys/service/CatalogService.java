package rikkeis.academys.service;

import rikkeis.academys.model.Author;
import rikkeis.academys.model.Catalog;
import rikkeis.academys.service.interfaces.ICatalogService;
import rikkeis.academys.util.ConnectDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CatalogService implements ICatalogService {
    @Override
    public List<Catalog> getAllCatalogs() {
        List<Catalog> catalogs = new ArrayList<>();
        Connection conn = ConnectDB.getConnection();
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM catalog");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Catalog catalog = new Catalog(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getBoolean("status")
                );
                catalogs.add(catalog);
            }
            return catalogs;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectDB.closeConnectio(conn);
        }

    }

    @Override
    public Catalog getCatalogById(int id) {
        return null;
    }

    @Override
    public void addCatalog(Catalog catalog) {

    }

    @Override
    public void save(Catalog catalog) {
        Connection conn = ConnectDB.getConnection();
        CallableStatement callSt = null;

        if (catalog.getId() == null) {
            try {
                callSt = conn.prepareCall("INSERT INTO catalog (name, status) VALUES (?,?)");
                callSt.setString(1, catalog.getName());
                callSt.setBoolean(2, catalog.isStatus());
                callSt.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } finally {
                ConnectDB.closeConnectio(conn);
            }
        } else {
            try {
                callSt = conn.prepareCall("UPDATE catalog SET name = ?, status = ? WHERE id = ?");
                callSt.setString(1, catalog.getName());
                callSt.setBoolean(2, catalog.isStatus());
                callSt.setInt(3, catalog.getId());
                callSt.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } finally {
                ConnectDB.closeConnectio(conn);
            }
        }
    }

    @Override
    public void deleteCatalog(int id) {
        Connection conn = ConnectDB.getConnection();
        try {
            // Check if there are any books in the category
            int bookCount = countBooksInCategory(id);
            if (bookCount > 0) {
                // If there are books, change the status to false (inactive)
                CallableStatement callSt = conn.prepareCall("update catalog set status = false where id = ?");
                callSt.setInt(1, id);
                callSt.executeUpdate();
            } else {
                // If there are no books, delete the category
                CallableStatement callSt = conn.prepareCall("delete from catalog where id = ?");
                callSt.setInt(1, id);
                callSt.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectDB.closeConnectio(conn);
        }

    }

    @Override
    public void changeCatalogStatus(int catalogId, boolean status) {

    }
    public int countBooksInCategory(Integer calalogId) {
        int count = 0;
        Connection conn = ConnectDB.getConnection();
        try {
            CallableStatement callSt = conn.prepareCall("select count(*) from book where catalog_id  = ?");
            callSt.setInt(1, calalogId);
            ResultSet rs = callSt.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectDB.closeConnectio(conn);
        }
        return count;
    }
}
