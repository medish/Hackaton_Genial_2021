package database.ManageModel;

import database.Model.Customer;
import database.persistence.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class ManageCustomer {

    private static SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    public void addCustomer(String id, String email, String firstName, boolean is_admin, String name)
    {
        Session session = sessionFactory.openSession();

        session.beginTransaction();
        Customer customer = new Customer();

        customer.setEmail(email);
        customer.setFirstName(firstName);
        customer.setIsAdmin(is_admin);
        customer.setName(name);
        customer.setId(id);

        session.save(customer);
        session.getTransaction().commit();

        session.close();
    }

    public void deleteCustomer(String customer_id)
    {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            Customer customer = session.get(Customer.class, customer_id);
            session.delete(customer);
            session.getTransaction().commit();
            session.close();
        } catch (HibernateException e) {
            if (session.getTransaction() != null) session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void updateCustomer(String customer_id, String email)
    {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            Customer customer = session.get(Customer.class, customer_id);
            customer.setEmail(email);
            session.update(customer);
            session.getTransaction().commit();
            session.close();
        } catch (HibernateException e) {
            if (session.getTransaction() != null) session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}