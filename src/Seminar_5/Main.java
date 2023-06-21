package Seminar_5;

import Seminar_5.controllers.UserController;
import Seminar_5.model.FileOperation;
import Seminar_5.model.FileOperationImpl;
import Seminar_5.model.Repository;
import Seminar_5.model.RepositoryFile;
import Seminar_5.views.ViewUser;

public class Main {
    public static void main(String[] args) {
        FileOperation fileOperation = new FileOperationImpl("users.txt");
        Repository repository = new RepositoryFile(fileOperation);
        UserController controller = new UserController(repository);
        ViewUser view = new ViewUser(controller);
        view.run();
    }
}
