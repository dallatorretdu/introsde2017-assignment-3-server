package unitn.dallatorre.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Id;
import javax.persistence.TypedQuery;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlAccessType;

import unitn.dallatorre.dao.PersonActivitiesDao;

@Entity
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="activityType")
public class ActivityType implements Serializable {
	@Id
    @XmlElement(name = "id", required = true)
    private String type;						// ID, not automatically Generated, it's a String

	public ActivityType() {
		
	}
	// Getters and setters
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	// persistence Methods
	public static ActivityType saveActivityType(ActivityType t) {
		EntityManager em = PersonActivitiesDao.instance.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.persist(t);
		tx.commit();
		PersonActivitiesDao.instance.closeConnections(em);
	    return t;
	}
	
	public static ActivityType updateActivityType(ActivityType t) {
		EntityManager em = PersonActivitiesDao.instance.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		t=em.merge(t);
		tx.commit();
		PersonActivitiesDao.instance.closeConnections(em);
	    return t;
	}
	
	public static void removeActivityType(ActivityType t) {
		EntityManager em = PersonActivitiesDao.instance.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
	    t=em.merge(t);
	    em.remove(t);
	    tx.commit();
	    PersonActivitiesDao.instance.closeConnections(em);
	}
	// FIND a type by it's ID
	public static ActivityType getById(String id) {
		EntityManager em = PersonActivitiesDao.instance.createEntityManager();
		ActivityType a = em.find(ActivityType.class, id);
		PersonActivitiesDao.instance.closeConnections(em);
		return a;
	}
	// GET all the activity types as a List using a Typed Query
	public static List<ActivityType> getAllTypes() {
		EntityManager em = PersonActivitiesDao.instance.createEntityManager();
		TypedQuery<ActivityType> query = em.createQuery("SELECT type FROM ActivityType type",ActivityType.class);
		return query.getResultList();
	}
	
	// Overridden equals method to check if the Type (String) is equals
	@Override
	public boolean equals(Object obj) {
		return this.getType().equals(((ActivityType) obj).getType());
	}
}