package md.ceiti.ma.indfxhibernate.model.implementations;

import md.ceiti.ma.indfxhibernate.dto.DateGrupa;
import md.ceiti.ma.indfxhibernate.model.entities.Grupa;
import md.ceiti.ma.indfxhibernate.model.entities.Profesor;
import md.ceiti.ma.indfxhibernate.model.interfaces.GrupeImplement;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import java.util.ArrayList;
import java.util.List;

public class GrupeImplementation implements GrupeImplement {
    public static final String GET_GRUPA = "from Grupa where idG = :id";
    public static final String GET_ALL_GRUPE = "from Grupa";
    public static final String GET_ID_BY_NAME =
            "SELECT idG FROM Grupa WHERE numeG = :numeG";
    public static final String GET_NAME_BY_ID =
            "SELECT numeG FROM Grupa WHERE idG = :id";
    public static final String GET_ALL_DATE_GRUPE =
            "SELECT new md.ceiti.ma.indfxhibernate.dto.DateGrupa(" +
                    "g.numeG, ROUND(AVG(m.mediaS), 2), " +
                    "SUM(m.absenteM + m.absenteN)) " +
                    "FROM Grupa g " +
                    "JOIN g.student s " +
                    "JOIN s.media m " +
                    "GROUP BY g.numeG";

    private static Configuration configuration = new Configuration();
    private SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

    @Override
    public Grupa get(int id) {
        try (Session session = sessionFactory.openSession()) {
            Query<Grupa> query = session.createQuery(GET_GRUPA, Grupa.class);
            query.setParameter("id", id);
            Grupa grupa = query.uniqueResult();
            return grupa;
        }
    }

    @Override
    public List<Grupa> getAll() {
        try (Session session = sessionFactory.openSession()) {
            Query<Grupa> query = session.createQuery(GET_ALL_GRUPE, Grupa.class);
            return query.list();
        }
    }

    @Override
    public void delete(int id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Grupa grupa = session.get(Grupa.class, id);
            session.remove(grupa);
            session.getTransaction().commit();
        }
    }

    @Override
    public void update(Grupa obj) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.merge(obj);
            session.getTransaction().commit();
        }
    }

    @Override
    public void create(Grupa obj) {
        configuration.configure();
        try (SessionFactory sessionFactory = configuration.buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Profesor profesor = session.get(Profesor.class, obj.getIdDir());
            Grupa grupa = Grupa.builder()
                    .idG(obj.getIdG())
                    .anAdm(obj.getAnAdm())
                    .numeG(obj.getNumeG())
                    .idDir(obj.getIdDir())
                    .profesor(profesor)
                    .build();
            session.persist(grupa);
            session.getTransaction().commit();
        }
    }

    @Override
    public ArrayList<String> getGroupNames() {
        List<Grupa> grupe = getAll();
        ArrayList <String> denumiri = new ArrayList<>();
        for(Grupa gr : grupe){
            denumiri.add(gr.getNumeG());
        }
        return denumiri;
    }

    @Override
    public int searchIdByGroupName(String ngr) {
        try (Session session = sessionFactory.openSession()) {
            Query<Integer> query = session.createQuery(GET_ID_BY_NAME, Integer.class);
            query.setParameter("numeG", ngr);
            Integer id = query.uniqueResult();
            return (id != null) ? id : -1;
        }
    }

    @Override
    public String searchGroupNameById(int id) {
        try (Session session = sessionFactory.openSession()) {
            Query<String> query = session.createQuery(GET_NAME_BY_ID, String.class);
            query.setParameter("id", id);
            String grupa = query.uniqueResult();
            return grupa;
        }
    }

    @Override
    public List<DateGrupa> getGroupData() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery(GET_ALL_DATE_GRUPE, DateGrupa.class).getResultList();
        }
    }
}
