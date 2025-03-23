package md.ceiti.ma.indfxhibernate.model.implementations;

import md.ceiti.ma.indfxhibernate.model.entities.Catedra;
import md.ceiti.ma.indfxhibernate.model.interfaces.CatedreImplement;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;

public class CatedreImplementation implements CatedreImplement {
    public static final String GET_CATEDRA = "from Catedra where idC = :id";
    public static final String GET_ALL_CATEDRE = "from Catedra";

    private static final Configuration configuration = new Configuration().configure();
    private final SessionFactory sessionFactory = configuration.buildSessionFactory();

    @Override
    public Catedra get(int id) {
        try (Session session = sessionFactory.openSession()) {
            Query<Catedra> query = session.createQuery(GET_CATEDRA, Catedra.class);
            query.setParameter("id", id);
            return query.uniqueResult();
        }
    }

    @Override
    public List<Catedra> getAll() {
        try (Session session = sessionFactory.openSession()) {
            Query<Catedra> query = session.createQuery(GET_ALL_CATEDRE, Catedra.class);
            return query.list();
        }
    }

    @Override
    public void delete(int id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Catedra catedra = session.get(Catedra.class, id);
            session.remove(catedra);
            session.getTransaction().commit();
        }
    }

    @Override
    public void update(Catedra obj) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.merge(obj);
            session.getTransaction().commit();
        }
    }

    @Override
    public void create(Catedra obj) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Catedra catedra = Catedra.builder()
                    .idC(obj.getIdC())
                    .nmCatedra(obj.getNmCatedra())
                    .build();
            session.persist(catedra);
            session.getTransaction().commit();
        }
    }
}
