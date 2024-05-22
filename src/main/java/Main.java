import org.hibernate.boot.Metadata;
import org.hibernate.*;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class Main {
    public static void main(String[] args) {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml").build();
        Metadata metadata = new MetadataSources(registry).getMetadataBuilder().build();
        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();

        Session session = sessionFactory.openSession();
        Course course = session.get(Course.class, 1);
        System.out.println("Name course: " + course.getName());

        Transaction transaction = session.beginTransaction();
        Course newCourse = new Course();
        newCourse.setName("New Course \"super java\"");
        newCourse.setCourseType(CourseType.BUSINESS);
        newCourse.setTeacherId(1);

        newCourse = session.get(Course.class, 48);
        newCourse.setName("New Course \"super java and MySQL from Ogar\"");

        course.setName("SQL for developers and designers");
        session.save(newCourse);
        session.save(course);
        session.delete(newCourse);

        transaction.commit();
        sessionFactory.close();
    }
}
