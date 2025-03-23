package md.ceiti.ma.indfxhibernate.model.implementations;

import md.ceiti.ma.indfxhibernate.model.entities.Catedra;
import md.ceiti.ma.indfxhibernate.model.entities.Profesor;
import md.ceiti.ma.indfxhibernate.model.interfaces.ProfesoriImplement;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;

public class ProfesoriImplementation implements ProfesoriImplement {
    public static final String GET_PROFESOR = "from Profesor where idP = :id";
    public static final String GET_ALL_PROFESORI = "from Profesor";

    private static final Configuration configuration = new Configuration().configure();
    private final SessionFactory sessionFactory = configuration.buildSessionFactory();

    @Override
    public Profesor get(int id) {
        try (Session session = sessionFactory.openSession()) {
            Query<Profesor> query = session.createQuery(GET_PROFESOR, Profesor.class);
            query.setParameter("id", id);
            return query.uniqueResult();
        }
    }

    @Override
    public List<Profesor> getAll() {
        try (Session session = sessionFactory.openSession()) {
            Query<Profesor> query = session.createQuery(GET_ALL_PROFESORI, Profesor.class);
            return query.list();
        }
    }

    @Override
    public void delete(int id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Profesor profesor = session.get(Profesor.class, id);
            if (profesor != null) {
                session.remove(profesor);
                session.getTransaction().commit();
            }
        }
    }

    @Override
    public void update(Profesor obj) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.merge(obj);
            session.getTransaction().commit();
        }
    }

    @Override
    public void create(Profesor obj) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Catedra catedra = session.get(Catedra.class, obj.getIdCatedra());
            Profesor profesor = Profesor.builder()
                    .idP(obj.getIdP())
                    .IDNP(obj.getIDNP())
                    .nume(obj.getNume())
                    .prenume(obj.getPrenume())
                    .salariu(obj.getSalariu())
                    .idCatedra(obj.getIdCatedra())
                    .catedra(catedra)
                    .build();
            session.persist(profesor);
            session.getTransaction().commit();
        }
    }
}