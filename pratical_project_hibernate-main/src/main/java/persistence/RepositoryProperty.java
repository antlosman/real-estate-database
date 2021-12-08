package persistence;


import model.Property;
import model.PropertyBrokerOwner;
import util.DBUtil;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.List;

public class RepositoryProperty {

    private EntityManager entityManager;

    public RepositoryProperty() {
        entityManager = DBUtil.getEntityManager();
    }

    public void saveProperty(Property property) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(property);
            entityManager.getTransaction().commit();
            System.out.println("Property is saved");
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
        }
    }

    public void updatePropertyPrice(int propertyId, int price) {
        try {
            String sql = "UPDATE Property p SET p.price = :newPrice WHERE p.propertyId = :id";
            entityManager.getTransaction().begin();
            entityManager
                    .createQuery(sql)
                    .setParameter("newPrice", price)
                    .setParameter("id", propertyId)
                    .executeUpdate();
            entityManager.getTransaction().commit();
            System.out.println("Property price is updated");
        } catch (Exception e) {
            e.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }

    public void deleteProperty(Property property) {
        try {
            entityManager.getTransaction().begin();
            entityManager.remove(entityManager.merge(property));
            entityManager.getTransaction().commit();
            System.out.println("Property is deleted");
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
        }
    }

    public Property findPropertyById(int propertyId) {
        return entityManager.find(Property.class, propertyId);
    }

    public Property findPropertyById2(int propertyId) {
        String sql = "FROM Property WHERE propertyId = :id";

        return entityManager
                .createQuery(sql, Property.class)
                .setParameter("id", propertyId)
                .getSingleResult();
    }

    public List<Property> listAllProperties() {
        List<Property> allPropertyList = null;
        try {
            allPropertyList = entityManager.createQuery("FROM Property", Property.class).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } return allPropertyList;
    }

    public List<Property> someYearPropertyList(LocalDate date) {

        String sql = "FROM Property as p WHERE YEAR(p.dateOfRegister)=year(:someYear)";

        return entityManager
                .createQuery(sql, Property.class)
                .setParameter("someYear", date)
                .getResultList();
    }

    public List<Property> betweenTwoDatesPropertyList(LocalDate firstDate, LocalDate secondDate) {

        String sql = "FROM Property WHERE dateOfRegister BETWEEN :date1 AND :date2";

        return entityManager
                .createQuery(sql, Property.class)
                .setParameter("date1", firstDate)
                .setParameter("date2", secondDate)
                .getResultList();
    }


    public List<Property> lastMonthPropertyList(LocalDate date) {

        String sql = "FROM Property as p WHERE MONTH(p.dateOfRegister)=month(:lastMonth)";

        return entityManager
                .createQuery(sql, Property.class)
                .setParameter("lastMonth", date)
                .getResultList();
    }

    public List<Property> lastWeekPropertyList(LocalDate firstDate, LocalDate secondDate) {

        String sql = "FROM Property WHERE dateOfRegister BETWEEN :date1 AND :date2";

        return entityManager
                .createQuery(sql, Property.class)
                .setParameter("date1", firstDate)
                .setParameter("date2", secondDate)
                .getResultList();
    }

    public void totalProperties() {

        String sql = "SELECT count(*) FROM Property";

        Long total = (Long) entityManager.createQuery(sql).getSingleResult();
        System.out.println("Total properties: " + total);

    }

    public List<PropertyBrokerOwner> listPropertyBrokerOwner() {
        String sql = "SELECT new model.PropertyBrokerOwner(p.propertyId, p.address, p.broker.name, p.owner.name)" +
                " FROM Property p";
        return  entityManager
                .createQuery(sql, PropertyBrokerOwner.class)
                .getResultList();
    }

}
