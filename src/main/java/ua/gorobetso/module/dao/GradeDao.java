package ua.gorobetso.module.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import ua.gorobetso.module.utils.HibernateFactoryUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GradeDao {
    private final SessionFactory sessionFactory;
    private static final StudentDao STUDENT_DAO = new StudentDao();

    public GradeDao() {
        this.sessionFactory = HibernateFactoryUtil.getSessionFactory();
    }

    public void avgGradeByStudentGroup() {
        Map<String, List<String>> studentsByGroups = STUDENT_DAO.getAllByGroup();
        Map<String, Double> result = new HashMap<>();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        for (Map.Entry<String, List<String>> entry : studentsByGroups.entrySet()) {
            for (int i = 0; i < entry.getValue().size(); i++) {
                result.put(entry.getKey(), session.createQuery("select avg(value) from Grade where student_id =:group", Double.class)
                        .setParameter("group", entry.getValue().get(i))
                        .uniqueResult());
            }
        }
        transaction.commit();
        session.close();
        result.entrySet()
                .stream()
                .forEach(System.out::println);
    }
}
