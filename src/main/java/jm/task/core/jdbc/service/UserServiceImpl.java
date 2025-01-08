package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.util.List;

public class UserServiceImpl implements UserService {
    private UserDaoJDBCImpl dao = new UserDaoJDBCImpl();
    private UserDaoHibernateImpl daoHibernate = new UserDaoHibernateImpl();

    @Override
    public void createUsersTable() {
//        dao.createUsersTable();
        daoHibernate.createUsersTable();
        System.out.println("Таблица создана");
    }

    @Override
    public void dropUsersTable() {
//        dao.dropUsersTable();
        daoHibernate.dropUsersTable();
        System.out.println("Таблица удалена");

    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
//        dao.saveUser(name,lastName,age);
        daoHibernate.saveUser(name,lastName,age);
        System.out.printf("Пользователь с именем %s добавлен в базу данных\n", name);
    }

    @Override
    public void removeUserById(long id) {
//        dao.removeUserById(id);
        daoHibernate.removeUserById(id);
        System.out.println("Запись успешно удалена");
    }

    @Override
    public List<User> getAllUsers() {
        return daoHibernate.getAllUsers();
//        return dao.getAllUsers();
    }

    @Override
    public void cleanUsersTable() {
//        dao.cleanUsersTable();
        daoHibernate.cleanUsersTable();
        System.out.println("Таблица очищена");
    }
}
