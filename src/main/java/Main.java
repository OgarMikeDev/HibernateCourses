import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml").build();
        Metadata metadata = new MetadataSources(registry)
                .getMetadataBuilder().build();
        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        //update course
        Course course = session.get(Course.class,1);
//        course.setName("Full-stack developer 0-PRO");
//        course.setType(CourseType.BUSINESS);
        int countStudents = course.getStudents().size();
        System.out.println("Course: " + course +
                "\nTeacher: " + course.getTeacher() +
                "\nCount students: " + countStudents);
        List<Student> studentsList = course.getStudents();
        studentsList.forEach(System.out::println);

        //create new course
        Course newCourse = new Course();
        newCourse.setName("New course Java for pro-developments!");
        newCourse.setDuration(10);
        newCourse.setType(CourseType.PROGRAMMING);
        newCourse.setDescription("new course java from Ogar!");
        newCourse.setTeacher(course.getTeacher());

        session.save(course);
        session.save(newCourse);
        transaction.commit();
        sessionFactory.close();
    }
}
