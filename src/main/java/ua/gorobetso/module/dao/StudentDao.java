package ua.gorobetso.module.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import ua.gorobetso.module.model.Student;
import ua.gorobetso.module.utils.HibernateFactoryUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudentDao {
    private final SessionFactory sessionFactory;

    private static final StudentGroupDao STUDENT_GROUP_DAO = new StudentGroupDao();

    public StudentDao() {
        this.sessionFactory = HibernateFactoryUtil.getSessionFactory();
    }

    public void getStudentsWhoseAverageGradeExpensiveThan(int than) {
        List<Student> studentList = getAllStudents();
        Map<Student, Double> result = new HashMap<>();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        for (Student student : studentList) {
            result.put(student, session.createQuery("select avg(value) from Grade where student_id =:student", Double.class)
                    .setParameter("student", student.getId())
                    .uniqueResult());
        }
        transaction.commit();
        session.close();
        result.entrySet().stream()
                .filter(studentDoubleEntry -> studentDoubleEntry.getValue() > than)
                .forEach(System.out::println);
    }

    public void countStudentsInStudentGroups() {
        List<String> studentGroups = STUDENT_GROUP_DAO.getAllGroups();
        Map<String, Long> result = new HashMap<>();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        for (String studentGroup : studentGroups) {
            result.put(studentGroup, session.createQuery("select count(Student) from Student where student_group_title =:group", Long.class)
                    .setParameter("group", studentGroup)
                    .uniqueResult());
        }
        transaction.commit();
        session.close();
        result.entrySet()
                .stream()
                .forEach(System.out::println);
    }

    public Map<String, List<String>> getAllByGroup() {
        List<String> studentGroups = STUDENT_GROUP_DAO.getAllGroups();
        Map<String, List<String>> result = new HashMap<>();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        for (String studentGroup : studentGroups) {
            result.put(studentGroup, session.createQuery("select id from Student where student_group_title =:group", String.class)
                    .setParameter("group", studentGroup)
                    .list());
        }
        transaction.commit();
        session.close();
        return result;
    }

    public List<Student> getAllStudents() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        List<Student> studentList = session.createQuery("select s from Student s", Student.class)
                .list();
        transaction.commit();
        session.close();
        return studentList;
    }
}
