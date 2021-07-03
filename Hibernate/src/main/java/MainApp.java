import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class MainApp {
    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration()
                .addAnnotatedClass(Product.class)
                .buildSessionFactory();
        Session session = null;

        // CREATE
//        session = sessionFactory.getCurrentSession();
//        session.beginTransaction();
//        Product product = new Product("Milk", 80);
//        System.out.println(product);
//        session.save(product);
//        System.out.println(product);
//        session.getTransaction().commit();

        // READ
//        session = sessionFactory.getCurrentSession();
//        session.beginTransaction();
//        Product productFromDb = session.get(Product.class, 1L);
//        System.out.println(productFromDb);
//        session.getTransaction().commit();

        // UPDATE
//        session = sessionFactory.getCurrentSession();
//        session.beginTransaction();
//        Product productFromDb = session.get(Product.class, 1L);
//        System.out.println(productFromDb);
//        productFromDb.setCost(100);
//        productFromDb.setTitle("Milk");
//        session.getTransaction().commit();

        // DELETE
//        session = sessionFactory.getCurrentSession();
//        session.beginTransaction();
//        Product productFromDb = session.get(Product.class, 1L);
//        session.remove(productFromDb);
//        session.getTransaction().commit();

        sessionFactory.close();
    }
}
