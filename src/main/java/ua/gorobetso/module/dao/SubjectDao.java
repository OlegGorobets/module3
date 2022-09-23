package ua.gorobetso.module.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import ua.gorobetso.module.model.Subject;
import ua.gorobetso.module.utils.HibernateFactoryUtil;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SubjectDao {
    private final SessionFactory sessionFactory;

    public SubjectDao() {
        this.sessionFactory = HibernateFactoryUtil.getSessionFactory();
    }

    public void getSubjectWithWorstAndBestPerformance() {
        List<Subject> subjects = getAllSubjects();
        Map<String, Long> result = new HashMap<>();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        for (Subject subject : subjects) {
            result.put(subject.toString(), session.createQuery("select sum(value) from Grade where subject.code =:subject", Long.class)
                    .setParameter("subject", subject.getCode())
                    .uniqueResult());
        }
        transaction.commit();
        session.close();
        System.out.println("Worst performance: " + result.entrySet().stream().min(Comparator.comparingLong(Map.Entry::getValue)).get());
        System.out.println("Best performance: " + result.entrySet().stream().max(Comparator.comparingLong(Map.Entry::getValue)).get());
    }

    public List<Subject> getAllSubjects() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        List<Subject> result = session.createQuery("select s from Subject s", Subject.class).list();
        transaction.commit();
        session.close();
        return result;
    }
}
