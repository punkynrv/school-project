package md.ceiti.ma.indfxhibernate.model.implementations;

import md.ceiti.ma.indfxhibernate.model.entities.Bursier;
import md.ceiti.ma.indfxhibernate.model.entities.Student;
import md.ceiti.ma.indfxhibernate.model.interfaces.BursieriImplement;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import java.util.List;

public class BursieriImplementation implements BursieriImplement {
    public static final String GET_BURSIER = "from Bursier where idS = :id";
    public static final String GET_ALL_BURSIERI = "from Bursier";
    public static final String DEL_ALL_BURSIERI = "delete from Bursier";

    private static final Configuration configuration = new Configuration().configure();
    private final SessionFactory sessionFactory = configuration.buildSessionFactory();


    @Override
    public Bursier get(int id) {
        try (Session session = sessionFactory.openSession()) {
            Query<Bursier> query = session.createQuery(GET_BURSIER, Bursier.class);
            query.setParameter("id", id);
            return query.uniqueResult();
        }
    }

    @Override
    public List<Bursier> getAll() {
        try (Session session = sessionFactory.openSession()) {
            Query<Bursier> query = session.createQuery(GET_ALL_BURSIERI, Bursier.class);
            return query.list();
        }
    }

    @Override
    public void delete(int id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Bursier bursier = session.find(Bursier.class, id);
            if (bursier != null) {
                Student student = bursier.getStudent();
                if (student != null) {
                    student.setBursier(null);
                }
                session.remove(bursier);
                session.getTransaction().commit();
            } else {
                session.getTransaction().rollback();
            }
        }
    }

    @Override
    public void update(Bursier obj) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.merge(obj);
            session.getTransaction().commit();
        }
    }

    @Override
    public void create(Bursier obj) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Student student = session.get(Student.class, obj.getIdS());
            Bursier bursier = Bursier.builder()
                    .idS(obj.getIdS())
                    .bursa(obj.getBursa())
                    .student(student)
                    .build();
            session.persist(bursier);
            session.getTransaction().commit();
        }
    }

    @Override
    public void deleteAll() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.createQuery(DEL_ALL_BURSIERI).executeUpdate();
            session.getTransaction().commit();
        }
    }

}
