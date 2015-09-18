package com.youama.nexus.core.base;

import com.youama.nexus.core.Log;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.Query;

import java.util.List;


/**
 * This generic class is a database service abstraction. All kind of services should extend this class and give the
 * entity model as a generic parameter. This class implements the basics of Hibernate data access functions.
 * 
 * @author David Belicza
 * @since 2015.08.21.
 */
public abstract class BaseService<T> {

	/**
	 * The session factory of Hibernate. All service has its own session for each thread.
	 */
    protected SessionFactory sessionFactory;

    /**
     * It sets the sessionFactory property.
     * 
     * @param sessionFactory The session factory of Hibernate.
     */
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * It creates a single new entity in the database and retrieves the created object. The transaction will be 
     * rollbacked if the saving method throws and exception.
     * 
     * @param entityModel Any entity model with Hibernate annotations.
     * @return The created entity object with newly created ID.
     */
    public T add(T entityModel) {
        Session session = null;
        Transaction transaction = null;

        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(entityModel);
            transaction.commit();
            session.refresh(entityModel);
        } catch (Exception e) {
            assert transaction != null;
            transaction.rollback();
            Log.warning(e);

        } finally {
            assert session != null;
            session.close();
        }

        return entityModel;
    }

    /**
	 * It updates an entity what already exists and retrieves the updated object. The transaction will be rollbacked if 
	 * the saving method throws and exception.
	 * 
	 * @param entityModel Any entity model with Hibernate annotations.
	 * @return The updated entity object.
	 */
    public T update(T entityModel) {
        Session session = null;
        Transaction transaction = null;

        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.update(entityModel);
            transaction.commit();
            session.refresh(entityModel);
        } catch (Exception e) {
            assert transaction != null;
            transaction.rollback();
            Log.warning(e);

        } finally {
            assert session != null;
            session.close();
        }

        return entityModel;
    }

    /**
	 * It updates or creates an entity and retrieves the updated object included the ID. The transaction will be 
	 * rollbacked if the saveOrUpdate method throws and exception.
	 * 
	 * @param entityModel Any entity model with Hibernate annotations.
	 * @return The created or updated entity object.
	 */
    public T save(T entityModel) {
        Session session = null;
        Transaction transaction = null;

        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.saveOrUpdate(entityModel);
            transaction.commit();
            session.refresh(entityModel);
        } catch (Exception e) {
            assert transaction != null;
            transaction.rollback();
            Log.warning(e);

        } finally {
            assert session != null;
            session.close();
        }

        return entityModel;
    }

    /**
     * It retrieves the Hibernate entity model or null by ID and the entity model class.
     * 
     * @param entityModelClass The class of the Hibernate entity model.
     * @param id The ID of any entity.
     * @return The found entity object or null.
     */
    public T getEntityById(Class<T> entityModelClass, long id) {
        Session session = null;
        T entityModel = null;

        try {
            session = sessionFactory.openSession();
            entityModel = session.get(entityModelClass, id);
        } catch (Exception e) {
            Log.warning(e);

        } finally {
            assert session != null;
            session.close();
        }

        return entityModel;
    }

    /**
     * It retrieves all entities from the entity table by the entity model class. It can be null when no results.
     *  
     * @param entityModelClass The class of the Hibernate entity model
     * @return The Hibernate entity model collection or null.
     */
    @SuppressWarnings("unchecked")
	public List<T> getCollection(Class<T> entityModelClass) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from " + entityModelClass.getSimpleName());

        List<T> entityModelCollection = query.list();
        session.close();

        return entityModelCollection;
    }

    // TODO bunch save, update, filtered collection0
}
