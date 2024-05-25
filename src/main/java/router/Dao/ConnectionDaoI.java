package router.Dao;

import router.model.User;
import router.model.UserUpdate;

import java.util.List;

public interface ConnectionDaoI {
    List<User> sortBy(String keyword);

    List<User> getUserByCountry( String keyword);
    User getUserById(Integer id);
    void addUser(User user);
    List<User> getAllUser();
    void deleteUser(Integer id);
}
