package router.service;

import router.Dao.ConnectionDaoI;
import router.Dao.ConnectionDaoImpl;
import router.model.User;
import java.util.List;

public class UserService implements UserServiceI {
    private ConnectionDaoI connect = new ConnectionDaoImpl();



    @Override
    public void addUser(User user) {
        connect.addUser(user);
    }

    @Override
    public List<User> getAllUser() {
        return connect.getAllUser();
    }

    @Override
    public User getUserById(Integer id) {
        return connect.getUserById(id);
    }

    @Override
    public void deleteUser(Integer id) {
        connect.deleteUser(id);
    }

    @Override
    public List<User> getUserByCountry(String keyword) {
        return connect.getUserByCountry(keyword);
    }

    @Override
    public List<User> sortBy(String keyword) {
          return connect.sortBy(keyword);
    }


}