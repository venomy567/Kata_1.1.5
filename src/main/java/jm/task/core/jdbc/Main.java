package jm.task.core.jdbc;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {

        UserService user = new UserServiceImpl();

        user.createUsersTable();

        user.saveUser("Chelovek", "Chelovechiche", (byte)127);
        user.saveUser("Pupok", "Mitii", (byte)5);
        user.saveUser("Dom", "Kriwa", (byte)10);
        user.saveUser("Luda", "Popchanks", (byte)11);

        System.out.println(user.getAllUsers());

        user.cleanUsersTable();
        user.dropUsersTable();
    }
}