package jm.task.core.jdbc.dao;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    private final SessionFactory sessionFactory = Util.creatSessionHibernate();

    public UserDaoHibernateImpl() {
    }

    @Override
    public void createUsersTable() {

        Transaction transaction = null;
        try(Session session = sessionFactory.openSession()) {

            transaction = session.beginTransaction();
            session.createNativeQuery(
                    "CREATE TABLE IF NOT EXISTS newtable (id BIGINT PRIMARY KEY AUTO_INCREMENT, FirstName VARCHAR(20), LastName VARCHAR(20), Age TINYINT)")
                    .executeUpdate();
            transaction.commit();

        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) transaction.rollback();
        }

    }

    @Override
    public void dropUsersTable() {

        Transaction transaction = null;
        try(Session session = sessionFactory.openSession()) {

            transaction = session.beginTransaction();
            session.createNativeQuery("DROP TABLE IF EXISTS newtable").executeUpdate();
            transaction.commit();

        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) transaction.rollback();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {

        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {

            transaction = session.beginTransaction();
            session.save(new User(name,lastName,age));
            transaction.commit();

        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) transaction.rollback();
        }
    }

    @Override
    public void removeUserById(long id) {

        Transaction transaction = null;
        try(Session session = sessionFactory.openSession()) {

            transaction = session.beginTransaction();
            session.delete(session.get(User.class, id));
            transaction.commit();

        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) transaction.rollback();
        }
    }

    @Override
    public List<User> getAllUsers() {

        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM User", User.class).getResultList();
        }
    }

    @Override
    public void cleanUsersTable() {

        Transaction transaction = null;
        try(Session session = sessionFactory.openSession()) {

            transaction = session.beginTransaction();
            session.createNativeQuery("TRUNCATE TABLE newtable").executeUpdate();
            transaction.commit();

        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) transaction.rollback();
        }
    }
}
