package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl();

        userService.createUsersTable();

        userService.saveUser("Никита", "Чайка", (byte)33);
        userService.saveUser("Георгий", "Чайка", (byte)31);
        userService.saveUser("Данил", "Чайка", (byte)21);
        userService.saveUser("Дарья", "Чайка", (byte)28);

        userService.removeUserById(1);

        userService.getAllUsers();

        userService.cleanUsersTable();

        userService.dropUsersTable();
    }
}
