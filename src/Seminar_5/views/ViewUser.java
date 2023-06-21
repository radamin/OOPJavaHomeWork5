package Seminar_5.views;

import Seminar_5.controllers.UserController;
import Seminar_5.model.User;
import Seminar_5.views.validator.NameAndSurnameValidator;

import java.util.List;
import java.util.Scanner;

public class ViewUser {

    private final UserController userController;

    public ViewUser(UserController userController) {
        this.userController = userController;
    }

    public void run(){
        Commands com;

        while (true) {
            String command = prompt("Введите команду: ");
            try { // Чтобы избежать ошибок используем try-catch;
                com = Commands.valueOf(command.toUpperCase()); // Используем toUpperCase() чтобы можно было вводить команды в разном регистре;
                if (com == Commands.EXIT) return;
                switch (com) {
                    case CREATE -> // Добавление пользователя;
                            createUser();
                    case READ -> // Чтение данных пользователя;
                            readUser();
                    case LIST -> // Чтение списка пользователей;
                            readList();
                    case UPDATE -> // Изменение записи о пользователе;
                            updateUser();
                    case DELETE -> // Удаление пользователя;
                            deleteUser();
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void deleteUser() {
        readList();

    }

    private void updateUser() throws Exception {
        readList();
        User user = getUser();
        User updatedtUser = getNewUser();
        updatedtUser.setId(user.getId());
        User savedUser = userController.updateUser(updatedtUser);
        System.out.println(savedUser);
    }

    private void readList() { // Метод вывода всех пользователей в терминал;
        List<User> listUsers = userController.readAllUsers();
        for (User user: listUsers) {
            System.out.println(user);
        }
    }

    private void readUser() throws Exception {
        User user = getUser();
        System.out.println(user);
    }

    private User getUser() throws Exception {
        String id = prompt("Идентификатор пользователя: ");
        return userController.readUser(id);
    }

    private void createUser() throws Exception {
        User user = getNewUser();
        userController.saveUser(user);
    }

    private User getNewUser() throws Exception {
        String firstName = prompt("Имя: ");
        new NameAndSurnameValidator(firstName).validate();
        String lastName = prompt("Фамилия: ");
        new NameAndSurnameValidator(lastName).validate();
        String phone = prompt("Номер телефона: ");
        return new User(firstName, lastName, phone);
    }

    private String prompt(String message) {
        Scanner in = new Scanner(System.in);
        System.out.print(message);
        return in.nextLine();
    }


}
