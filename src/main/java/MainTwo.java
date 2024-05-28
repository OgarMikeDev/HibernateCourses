import org.hibernate.query.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;

public class MainTwo {
    public static void main(String[] args) {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml").build();
        Metadata metadata = new MetadataSources(registry)
                .getMetadataBuilder().build();
        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();

        Session session = sessionFactory.openSession();

//        String hql = "From " + Course.class.getName() + " WHERE price >= 100000 ORDER BY price desc";
//        List<Course> courses = session.createQuery(hql).setMaxResults(5).getResultList();
//        courses.forEach(elem -> System.out.println(elem.getPrice()));

//        String hql = "SELECT count(course_id)/(SELECT MAX(MONTH(subscription_date)) " +
//                "FROM " + Subscription.class.getName() + ") as count, " +
//                "course_id FROM " + Subscription.class.getName() + " GROUP BY course_id";

        String hql = "SELECT s.courseId, COUNT(*) " +
                "FROM Subscription s\n" +
                "GROUP BY s.courseId\n";

        Query query = session.createQuery(hql, Subscription[].class);
        List<String[]> resultList = query.getResultList();

        for (Object[] row : resultList) {
//            int courseId = Integer.parseInt(row[0]);
//            float totalSubscriptions = Float.parseFloat(row[1]);
            int courseId = (int) row[0];
            float totalSubscriptions = (long) row[1];
            System.out.println("Course ID: " + courseId + ", Total Subscriptions: " + totalSubscriptions / 9);
        }

        sessionFactory.close();
    }
}
