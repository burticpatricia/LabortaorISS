package mo.persistence.orm;

import mo.model.Client;
import mo.model.Produs;
import mo.persistence.IProdusRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class ProduseOrmRepository implements IProdusRepository {
    private SessionFactory sessionFactory;

    public ProduseOrmRepository() {
        sessionFactory = HibernateUtils.getInstance();
    }

    public ProduseOrmRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void add(Produs produs) {

    }

    @Override
    public void delete(Produs produs) {

    }

    @Override
    public void update(Produs produs) {

    }

    @Override
    public Produs findOne(Long aLong) {
        return null;
    }

    @Override
    public List<Produs> findAll() {
        List produse = null;
        try(Session session = sessionFactory.openSession()){
            Transaction transaction = null;
            try{
                transaction = session.beginTransaction();
                System.out.println("ProduseOrmRepository findAll...session.beginTransaction() ");
                Query q = session.createQuery("from Produs");
                produse = q.list();
                System.out.println("ProduseOrmRepository findAll...session.createQuery()");
                transaction.commit();
                System.out.println("ProduseOrmRepository findAll...transaction.commit()");
            }catch(RuntimeException e){
                if (transaction != null) transaction.rollback();
            }
        }
        System.out.println("Exiting with value "+ (long) produse.size());
        return produse;
    }
}
