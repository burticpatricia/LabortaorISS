package mo.persistence.orm;

import mo.model.Client;
import mo.model.Comanda;
import mo.persistence.IComandaRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class ComenziOrmRepository implements IComandaRepository {
    private SessionFactory sessionFactory;

    public ComenziOrmRepository() {
        sessionFactory = HibernateUtils.getInstance();
    }
    @Override
    public void add(Comanda comanda) {
        try(Session session = sessionFactory.openSession()){
            Transaction transaction = null;
            try{
                transaction = session.beginTransaction();
                System.out.println("ComenziOrmRepositoryComenziOrmRepository add...session.beginTransaction() ");
                session.save(comanda);
                System.out.println("ComenziOrmRepository add...session.createQuery()");
                transaction.commit();
                System.out.println("ComenziOrmRepository add...transaction.commit()");
            }catch(RuntimeException e){
                if (transaction != null) transaction.rollback();
            }
        }
    }

    @Override
    public void delete(Comanda comanda) {
        try(Session session = sessionFactory.openSession()){
            Transaction transaction = null;
            try{
                transaction = session.beginTransaction();
                System.out.println("ComenziOrmRepositoryComenziOrmRepository delete...session.beginTransaction() ");
                session.delete(comanda);
                System.out.println("ComenziOrmRepository delete...session.createQuery()");
                transaction.commit();
                System.out.println("ComenziOrmRepository delete...transaction.commit()");
            }catch(RuntimeException e){
                if (transaction != null) transaction.rollback();
            }
        }

    }

    @Override
    public void update(Comanda comanda) {

    }

    @Override
    public Comanda findOne(Long aLong) {
        return null;
    }

    @Override
    public List<Comanda> findAll() {
        return null;
    }

    @Override
    public List<Comanda> getClientOrders(Client client) {
        List orders = new ArrayList<>();
        try(Session session = sessionFactory.openSession()){
            Transaction transaction = null;
            try{
                transaction = session.beginTransaction();
                System.out.println("ComenziOrmRepository getClientOrders...session.beginTransaction()");
                Query q = session.createQuery("from Comanda where client_id = :var");
                q.setParameter("var",client.getId());
                orders = q.list();
                System.out.println("ComenziOrmRepository getClientOrders...session.createQuery()");
                transaction.commit();
                System.out.println("ComenziOrmRepository getClientOrders...transaction.commit()");
            }catch(RuntimeException e){
                if (transaction != null) transaction.rollback();
            }
        }
        System.out.println("Exiting with "+orders.size()+" values");
        return orders;
    }
}
