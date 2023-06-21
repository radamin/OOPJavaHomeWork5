package Seminar_5.model;

import java.util.ArrayList;
import java.util.List;

public class RepositoryFile implements Repository {
    private final UserMapper mapper = new UserMapper();
    private final FileOperation fileOperation;

    public RepositoryFile(FileOperation fileOperation) {
        this.fileOperation = fileOperation;
    }

    @Override
    public List<User> getAllUsers() {
        List<String> lines = fileOperation.readAllLines();
        List<User> users = new ArrayList<>();
        for (String line : lines) {
            users.add(mapper.map(line));
        }
        return users;
    }

    @Override
    public void CreateUser(User user) {

        List<User> users = getAllUsers();
        int max = 0;
        for (User item : users) {
            int id = Integer.parseInt(item.getId());
            if (max < id){
                max = id;
            }
        }
        int newId = max + 1;
        String id = String.format("%d", newId);
        user.setId(id);
        users.add(user);
        List<String> lines = mapToString(users);
        fileOperation.saveAllLines(lines);
    }

    private List<String> mapToString(List<User> users) {
        List<String> lines = new ArrayList<>();
        for (User item: users) {
            lines.add(mapper.map(item));
        }
        return lines;
    }

    @Override
    public User updateUser(User user) {
        List<User> users = getAllUsers();
        for (User currentUser: users) {
            if (currentUser.getId().equals(user.getId())) {
                currentUser.setFirstName(user.getFirstName());
                currentUser.setLastName(user.getLastName());
                currentUser.setPhone(user.getPhone());
            }
        }
        fileOperation.saveAllLines(mapToString(users));
        return user;
    }

    @Override
    public void deleteUser(String userId) throws Exception {
        List<User> users = getAllUsers();
        boolean found = false;
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId().equals(userId)) {
                users.remove(i);
                found = true;
                break;
            }
        }
        if (found) {
            List<String> lines = mapToString(users);
            fileOperation.saveAllLines(lines);
        } else {
            throw new Exception("User not found");
        }
    }


}
