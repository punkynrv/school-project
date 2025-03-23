package md.ceiti.ma.indfxhibernate.model.implementations;

import md.ceiti.ma.indfxhibernate.model.entities.Exmatriculat;
import md.ceiti.ma.indfxhibernate.model.entities.Grupa;
import md.ceiti.ma.indfxhibernate.model.interfaces.ExmatriculatiImplement;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;

public class ExmatriculatiImplementation implements ExmatriculatiImplement {

    private static Configuration configuration = new Configuration();
    private SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

    public static final String GET_EXMATRICULAT = "from Exmatriculat where idS = :id";
    public static final String GET_ALL_EXMATRICULATI = "from Exmatriculat";
    public static final String GET_EXM_ROW_COUNT = "SELECT COUNT(idS) FROM Exmatriculat";

    @Override
    public Exmatriculat get(int id) {
        try (Session session = sessionFactory.openSession()) {
            Query<Exmatriculat> query = session.createQuery(
                    GET_EXMATRICULAT, Exmatriculat.class);
            query.setParameter("id", id);
            Exmatriculat exmatriculat = query.uniqueResult();
            return exmatriculat;
        }
    }

    @Override
    public List<Exmatriculat> getAll() {
        try (Session session = sessionFactory.openSession()) {
            Query<Exmatriculat> query = session.createQuery(
                    GET_ALL_EXMATRICULATI, Exmatriculat.class);
            return query.list();
        }
    }

    @Override
    public void delete(int id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Exmatriculat exmatriculat = session.get(Exmatriculat.class, id);
            session.remove(exmatriculat);
            session.getTransaction().commit();
        }
    }

    @Override
    public void update(Exmatriculat obj) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.merge(obj);
            session.getTransaction().commit();
        }
    }

    @Override
    public void create(Exmatriculat obj) {
        configuration.configure();
        try (SessionFactory sessionFactory = configuration.buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Grupa grupa = session.get(Grupa.class, obj.getIdG());
            Exmatriculat exmatriculat = Exmatriculat.builder()
                    .idS(obj.getIdS())
                    .IDNP(obj.getIDNP())
                    .nume(obj.getNume())
                    .prenume(obj.getPrenume())
                    .idG(obj.getIdG())
                    .mediaS(obj.getMediaS())
                    .absenteM(obj.getAbsenteM())
                    .absenteN(obj.getAbsenteN())
                    .grupa(grupa)
                    .build();
            session.persist(exmatriculat);
            session.getTransaction().commit();
        }
    }

    @Override
    public int getRowCount() {
        try (Session session = sessionFactory.openSession()) {
            Query<Long> query = session.createQuery(GET_EXM_ROW_COUNT, Long.class);
            Long count = query.uniqueResult();
            return (count != null) ? count.intValue() : -1;
        }
    }
}
