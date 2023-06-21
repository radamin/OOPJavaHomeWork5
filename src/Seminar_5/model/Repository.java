package Seminar_5.model;

import java.util.List;

public interface Repository {
    List<User> getAllUsers();
    void CreateUser(User user);
    User updateUser(User user);
    void deleteUser(String userId) throws Exception;
//    void deleteOneUser(String userId) throws Exception;
}
