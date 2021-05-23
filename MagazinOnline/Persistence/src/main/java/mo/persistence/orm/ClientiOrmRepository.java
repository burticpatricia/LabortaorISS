package mo.persistence.orm;

import mo.model.Client;
import mo.persistence.IClientRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class ClientiOrmRepository implements IClientRepository {
    private SessionFactory sessionFactory;

    public ClientiOrmRepository() {
        sessionFactory = HibernateUtils.getInstance();
    }

    @Override
    public Client findByUsernameAndPassword(String username, String password) {
        Client user = null;
        try(Session session = sessionFactory.openSession()){
            Transaction transaction = null;
            try{
                transaction = session.beginTransaction();
                System.out.println("UsersORMRepository findUserByUsername...session.beginTransaction() "+username+" "+password);
                Query q = session.createQuery("from Client where nume_utilizator = :var and parola = :var1");
                q.setParameter("var",username);
                q.setParameter("var1",password);
                user = (Client)q.uniqueResult();
                System.out.println("UsersORMRepository findUserByUsername...session.createQuery()");
                transaction.commit();
                System.out.println("UsersORMRepository findUserByUsername...transaction.commit()");
            }catch(RuntimeException e){
                if (transaction != null) transaction.rollback();
            }
        }
        System.out.println("Exiting with value "+user);
        return user;
    }

    @Override
    public void add(Client client) {

    }

    @Override
    public void delete(Client client) {

    }

    @Override
    public void update(Client client) {
        try(Session session = sessionFactory.openSession()){
            Transaction transaction = null;
            try{
                transaction = session.beginTransaction();
                System.out.println("UsersORMRepository update...session.beginTransaction()");
                session.update(client);
                System.out.println("UsersORMRepository update...session.createQuery()");
                transaction.commit();
                System.out.println("UsersORMRepository update...transaction.commit()");
            }catch(RuntimeException e){
                if (transaction != null) transaction.rollback();
            }
        }
    }

    @Override
    public Client findOne(Long aLong) {
        return null;
    }

    @Override
    public List<Client> findAll() {
        return null;
    }
}
