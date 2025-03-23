package md.ceiti.ma.indfxhibernate.model.implementations;

import md.ceiti.ma.indfxhibernate.model.entities.Media;
import md.ceiti.ma.indfxhibernate.model.entities.Student;
import md.ceiti.ma.indfxhibernate.model.interfaces.MediiImplement;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import java.util.List;

public class MediiImplementation implements MediiImplement {
    public static final String GET_MEDIA = "from Media where idS = :id";
    public static final String GET_ALL_MEDIA = "from Media";

    private static final Configuration configuration = new Configuration().configure();
    private final SessionFactory sessionFactory = configuration.buildSessionFactory();

    @Override
    public Media get(int id) {
        try (Session session = sessionFactory.openSession()) {
            Query<Media> query = session.createQuery(GET_MEDIA, Media.class);
            query.setParameter("id", id);
            return query.uniqueResult();
        }
    }

    @Override
    public List<Media> getAll() {
        try (Session session = sessionFactory.openSession()) {
            Query<Media> query = session.createQuery(GET_ALL_MEDIA, Media.class);
            return query.list();
        }
    }

    @Override
    public void delete(int id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Media media = session.get(Media.class, id);
            session.remove(media);
            session.getTransaction().commit();
        }
    }

    @Override
    public void update(Media obj) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.merge(obj);
            session.getTransaction().commit();
        }
    }

    @Override
    public void create(Media obj) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Student student = session.get(Student.class, obj.getIdS());
            Media media = Media.builder()
                    .idS(obj.getIdS())
                    .mediaS(obj.getMediaS())
                    .absenteM(obj.getAbsenteM())
                    .absenteN(obj.getAbsenteN())
                    .student(student)
                    .build();
            session.persist(media);
            session.getTransaction().commit();
        }
    }
}

