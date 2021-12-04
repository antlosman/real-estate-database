package persistence;

import model.Broker;
import util.DBUtil;

import javax.persistence.EntityManager;
import java.util.List;

public class RepositoryBroker {

    private EntityManager entityManager;

    public RepositoryBroker() {
        entityManager = DBUtil.getEntityManager();
    }

    public void saveBroker(Broker broker) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(broker);
            entityManager.getTransaction().commit();
            System.out.println("New owner saved");
        } catch (Exception e) {
            e.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }

    public Broker findBrokerById(int brokerId) {
        return entityManager.find(Broker.class, brokerId);
    }

    public List<Broker> brokerList() {
        List<Broker> allBrokers = null;
        try {
            allBrokers = entityManager.createQuery("FROM Broker", Broker.class).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } return allBrokers;
    }

    public void deleteBroker(Broker broker) {
        try {
            entityManager.getTransaction().begin();
            entityManager.remove(entityManager.merge(broker));
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
        }
    }


}
