package ua.gorobetso.module.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import ua.gorobetso.module.model.Teacher;
import ua.gorobetso.module.utils.HibernateFactoryUtil;

import java.util.List;

public class TeacherDao {
    private final SessionFactory sessionFactory;
    private static final StudentDao STUDENT_DAO = new StudentDao();

    public TeacherDao() {
        this.sessionFactory = HibernateFactoryUtil.getSessionFactory();
    }

    public void searchByNameOrSurname(String nameOrSurname) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        List<Teacher> result = session
                .createQuery("select t from Teacher t where t.name =:nameOrSurname or t.surname =:nameOrSurname", Teacher.class)
                .setParameter("nameOrSurname", nameOrSurname).list();
        transaction.commit();
        session.close();
        result.stream()
                .forEach(System.out::println);
    }
}
