package database.ManageModel;

import server.Model.RoomType;
import database.persistence.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class ManageRoomType {

    private static SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    public void addRoomType(String name)
    {
        Session session = sessionFactory.openSession();

        session.beginTransaction();
        RoomType roomType = new RoomType(name);
        session.save(roomType);
        session.getTransaction().commit();

        session.close();
    }

    public void deleteRoomType(String name)
    {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            RoomType roomType = session.get(RoomType.class, name);
            session.delete(roomType);
            session.getTransaction().commit();
            session.close();
        } catch (HibernateException e) {
            if (session.getTransaction() != null) session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void updateRoomType(String name, String new_name)
    {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            RoomType roomType = session.get(RoomType.class, name);
            roomType.setName(new_name);
            session.update(roomType);
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