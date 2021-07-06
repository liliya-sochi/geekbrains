import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;

@Repository
public class ProductRepository {
    private Product product;

    SessionFactory sessionFactory = new Configuration()
            .addAnnotatedClass(Product.class)
            .buildSessionFactory();

    Session session = null;

    public ProductRepository() {
    }

    public void create(String title, int cost) {
        session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        product = new Product(title, cost);
        System.out.println(product);
        session.save(product);
        System.out.println(product);
        session.getTransaction().commit();
    }

    public void read(long id) {
        session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        product = session.get(Product.class, id);
        System.out.println(product);
        session.getTransaction().commit();
    }

    public void update(long id, String newTitle, int newCost) {
        session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        product = session.get(Product.class, id);
        System.out.println(product);
        product.setCost(newCost);
        product.setTitle(newTitle);
        session.getTransaction().commit();
    }

    public void delete(long id) {
        session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        product = session.get(Product.class, id);
        session.remove(product);
        session.getTransaction().commit();
    }
}
