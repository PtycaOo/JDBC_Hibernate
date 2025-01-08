package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {


    public UserDaoHibernateImpl() {
    }

    @Override
    public void createUsersTable() {
        try (Session session = Util.getSession()) {
            session.beginTransaction();
            String sql = "CREATE TABLE IF NOT EXISTS users " +
                    "(id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY, " +
                    "name VARCHAR(50) NOT NULL, lastName VARCHAR(50) NOT NULL, " +
                    "age TINYINT NOT NULL)";

            Query query = session.createSQLQuery(sql).addEntity(User.class);
            query.executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void dropUsersTable() {
        try (Session session = Util.getSession()) {
            session.beginTransaction();
            Query query = session.createSQLQuery(" DROP TABLE IF EXISTS users").addEntity(User.class);
            query.executeUpdate();
            session.getTransaction().commit();
        } catch (Exception s) {
            s.printStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Transaction transaction = null;
        try(Session session = Util.getSession()) {
            transaction = session.beginTransaction();
            User user = new User();
            user.setName(name);
            user.setLastName(lastName);
            user.setAge(age);
            session.save(user);
            transaction.commit();
        } catch (HibernateException e) {
            if(transaction != null){
                transaction.rollback();
                e.printStackTrace();
            }
        }
    }

    @Override
    public void removeUserById(long id) {
        Transaction transaction = null;
        try(Session session = Util.getSession()){
            transaction = session.beginTransaction();
            User user = session.get(User.class,id);
            session.delete(user);
            transaction.commit();
        } catch (HibernateException e) {
            if(transaction != null) {
                transaction.rollback();
                e.printStackTrace();
            }
        }
    }

//    @Override
//    public List<User> getAllUsers() {
//        List<User> userList = new ArrayList<>();
//
//        try(Session session = Util.getSession()){
//            Query query = session.createQuery("FROM User");
//            userList = query.list();
//        } catch (HibernateException e){
//            e.printStackTrace();
//        }
//        return userList;
//    }

    /* Так получается короче, но в таком методе у меня не получается отловить ошибки, так как надо 2 раза
    вызывать return.
     */
    @Override
    public List<User> getAllUsers() {
        try (Session session = Util.getSession()) {
            return session.createQuery("FROM User", User.class).list();
        } catch (HibernateException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void cleanUsersTable() {
        Transaction transaction = null;
        try(Session session = Util.getSession()){
            transaction = session.beginTransaction();
            Query query = session.createQuery("delete User");
            query.executeUpdate();
            transaction.commit();
        } catch (HibernateException e) {
            System.err.println("Ошибка при очистке таблицы " + e.getMessage());
            if(transaction != null) {
                transaction.rollback();
            }
        }
    }
}
