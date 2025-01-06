package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.util.List;

public class UserServiceImpl implements UserService {
    private UserDaoJDBCImpl dao = new UserDaoJDBCImpl();

    @Override
    public void createUsersTable() {
        dao.createUsersTable();
        System.out.println("Таблица создана");
    }

    @Override
    public void dropUsersTable() {
        dao.dropUsersTable();
        System.out.println("Таблица удалена");

    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        dao.saveUser(name,lastName,age);
        System.out.printf("Пользователь с именем %s добавлен в базу данных\n", name);
    }

    @Override
    public void removeUserById(long id) {
        dao.removeUserById(id);
        System.out.println("Запись успешно удалена");
    }

    @Override
    public List<User> getAllUsers() {
        return dao.getAllUsers();
    }

    @Override
    public void cleanUsersTable() {
        dao.cleanUsersTable();
        System.out.println("Таблица очищена");
    }
}
