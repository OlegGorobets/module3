package ua.gorobetso.module.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import ua.gorobetso.module.model.StudentGroup;
import ua.gorobetso.module.utils.HibernateFactoryUtil;

import java.util.List;

public class StudentGroupDao {
    private final SessionFactory sessionFactory;

    public StudentGroupDao() {
        this.sessionFactory = HibernateFactoryUtil.getSessionFactory();
    }

    public void searchByTitle(String title) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        List<StudentGroup> result = session
                .createQuery("select s from StudentGroup s where s.title like :title", StudentGroup.class)
                .setParameter("title", "%" + title + "%").list();
        transaction.commit();
        session.close();
        result.stream()
                .forEach(System.out::println);
    }

    public List<String> getAllGroups() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        List<String> result = session.createQuery("select title from StudentGroup", String.class).list();
        transaction.commit();
        session.close();
        return result;
    }
}
