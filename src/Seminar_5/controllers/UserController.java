package Seminar_5.controllers;

import Seminar_5.model.Repository;
import Seminar_5.model.User;

import java.util.List;

public class UserController {
    private final Repository repository;

    public UserController(Repository repository) {
        this.repository = repository;
    }

    public void saveUser(User user) {
        repository.CreateUser(user);
    }

    public User readUser(String userId) throws Exception {
        List<User> users = repository.getAllUsers();
        for (User user : users) {
            if (user.getId().equals(userId)) {
                return user;
            }
        }

        throw new Exception("User not found");
    }
    public List<User> readAllUsers () {
        return repository.getAllUsers();
    }

    public User updateUser(User user) {
        return repository.updateUser(user);

    }

}
