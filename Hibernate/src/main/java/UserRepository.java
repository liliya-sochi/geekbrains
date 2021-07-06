import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {
    private User user;

    SessionFactory sessionFactory = new Configuration()
            .addAnnotatedClass(Product.class)
            .buildSessionFactory();

    Session session = null;

    public UserRepository() {
    }

    public void create(String name) {
        session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        user = new User(name);
        System.out.println(user);
        session.save(user);
        System.out.println(user);
        session.getTransaction().commit();
    }

    public void read(long id) {
        session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        user = session.get(User.class, id);
        System.out.println(user);
        session.getTransaction().commit();
    }

    public void update(long id, String newName) {
        session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        user = session.get(User.class, id);
        System.out.println(user);
        user.setName(newName);
        session.getTransaction().commit();
    }

    public void delete(long id) {
        session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        user = session.get(User.class, id);
        session.remove(user);
        session.getTransaction().commit();
    }

}
