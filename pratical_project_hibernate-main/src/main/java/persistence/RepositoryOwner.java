package persistence;

import model.Owner;
import util.DBUtil;

import javax.persistence.EntityManager;
import java.util.List;

public class RepositoryOwner {

    private EntityManager entityManager;

    public RepositoryOwner() {
        entityManager = DBUtil.getEntityManager();
    }

    public void saveOwner(Owner owner) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(owner);
            entityManager.getTransaction().commit();
            System.out.println("New owner saved");
        } catch (Exception e) {
            e.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }

    public Owner findOwnerById(int ownerId) {
        return entityManager.find(Owner.class, ownerId);
    }

    public void updateOwnerPhoneNumber(int ownerId, String phoneNumber) {
        try {
            String sql = "UPDATE Owner o SET o.phone = :newPhoneNumber WHERE o.ownerId = :id";
            entityManager.getTransaction().begin();
            entityManager
                    .createQuery(sql)
                    .setParameter("newPhoneNumber", phoneNumber)
                    .setParameter("id", ownerId)
                    .executeUpdate();
            entityManager.getTransaction().commit();
            System.out.println("Owners phone number is updated");
        } catch (Exception e) {
            e.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }

    public List<Owner> ownerList() {
        List<Owner> allOwners = null;
        try {
            allOwners = entityManager.createQuery("FROM Owner", Owner.class).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } return allOwners;
    }

    public void deleteOwner(Owner owner) {
        try {
            entityManager.getTransaction().begin();
            entityManager.remove(entityManager.merge(owner));
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
        }
    }
}
