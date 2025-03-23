package md.ceiti.ma.indfxhibernate.model.implementations;

import md.ceiti.ma.indfxhibernate.model.entities.Grupa;
import md.ceiti.ma.indfxhibernate.model.entities.Student;
import md.ceiti.ma.indfxhibernate.model.interfaces.StudentiImplement;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import java.util.List;

public class StudentiImplementation implements StudentiImplement {

    private static Configuration configuration = new Configuration();
    private SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

    public static final String GET_STUDENT = "from Student where idS = :id";
    public static final String GET_ALL_STUDENTI = "from Student";
    public static final String GET_REUSITE =  "FROM Student where media.mediaS >= 5";
    public static final String GET_RESTANTIERI =  "FROM Student where media.mediaS < 5";
    public static final String GET_REUSITE_BEST = "FROM Student WHERE media.mediaS >= 8.5 " +
            "AND media.absenteN <= 40 ORDER BY media.mediaS DESC";
    public static final String GET_SMALLEST_MEDIA = "FROM Student WHERE media.mediaS = " +
            "(SELECT MIN(mediaS) FROM Media)";
    public static final String GET_BIGGEST_MEDIA = "FROM Student WHERE media.mediaS = " +
            "(SELECT MAX(mediaS) FROM Media)";
    public static final String SEARCH_ID_BY_IDNP = "SELECT idS FROM Student WHERE IDNP = :idnp";
    public static final String GET_ROW_COUNT = "SELECT COUNT(idS) FROM Student";


    @Override
    public Student get(int id) {
        try (Session session = sessionFactory.openSession()) {
            Query<Student> query = session.createQuery(GET_STUDENT, Student.class);
            query.setParameter("id", id);
            Student student = query.uniqueResult();
            return student;
        }
    }

    @Override
    public List<Student> getAll() {
        try (Session session = sessionFactory.openSession()) {
            Query<Student> query = session.createQuery(GET_ALL_STUDENTI, Student.class);
            return query.list();
        }
    }

    @Override
    public void delete(int id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Student student = session.get(Student.class, id);
            session.remove(student);
            session.getTransaction().commit();
        }
    }

    @Override
    public void update(Student obj) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.merge(obj);
            session.getTransaction().commit();
        }
    }

    @Override
    public void create(Student obj) {
        configuration.configure();
        try (SessionFactory sessionFactory = configuration.buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            Grupa grupa = session.get(Grupa.class, obj.getIdG());
            session.beginTransaction();
            Student student = Student.builder()
                    .idS(obj.getIdS())
                    .IDNP(obj.getIDNP())
                    .nume(obj.getNume())
                    .prenume(obj.getPrenume())
                    .idG(obj.getIdG())
                    .grupa(grupa)
                    .build();
            session.persist(student);
            session.getTransaction().commit();
        }
    }

    @Override
    public int searchByIDNP(String IDNP) {
        try (Session session = sessionFactory.openSession()) {
            Query<Integer> query = session.createQuery(SEARCH_ID_BY_IDNP, Integer.class);
            query.setParameter("idnp", IDNP);
            Integer id = query.uniqueResult();
            return (id != null) ? id : -1;
        }
    }

    @Override
    public int getRowCount() {
        try (Session session = sessionFactory.openSession()) {
            Query<Long> query = session.createQuery(GET_ROW_COUNT, Long.class);
            Long count = query.uniqueResult();
            return (count != null) ? count.intValue() : -1;
        }
    }


    @Override
    public List<Student> getReusite() {
        try (Session session = sessionFactory.openSession()) {
            Query<Student> query = session.createQuery(GET_REUSITE, Student.class);
            return query.list();
        }
    }

    @Override
    public List<Student> getReusiteBest() {
        try (Session session = sessionFactory.openSession()) {
            Query<Student> query = session.createQuery(GET_REUSITE_BEST, Student.class);
            return query.list();
        }
    }

    @Override
    public List<Student> getRestantieri() {
        try (Session session = sessionFactory.openSession()) {
            Query<Student> query = session.createQuery(GET_RESTANTIERI, Student.class);
            return query.list();
        }
    }

    @Override
    public List<Student> getSmallestMed() {
        try (Session session = sessionFactory.openSession()) {
            Query<Student> query = session.createQuery(GET_SMALLEST_MEDIA, Student.class);
            return query.list();
        }
    }

    @Override
    public List<Student> getBiggestMed() {
        try (Session session = sessionFactory.openSession()) {
            Query<Student> query = session.createQuery(GET_BIGGEST_MEDIA, Student.class);
            return query.list();
        }
    }

}
