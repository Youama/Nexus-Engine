package com.youama.nexus.core.base;

import com.youama.nexus.core.Log;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.Query;

import java.util.List;


/**
 * @author David Belicza
 * @since 2015.08.21.
 */
public abstract class BaseService<T> {

    protected SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public long save(T entityModel) {
        Session session = null;
        Transaction transaction = null;
        long entityModelId = 0L;

        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            entityModelId = (long) session.save(entityModel);
            transaction.commit();
        } catch (Exception e) {
            assert transaction != null;
            transaction.rollback();
            Log.warning(e);

        } finally {
            assert session != null;
            session.close();
        }

        return entityModelId;
    }

    public List<T> getCollection(Class<T> entityModelClass) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from " + entityModelClass.getSimpleName());

        List<T> entityModelCollection = query.list();
        session.close();

        return entityModelCollection;
    }

    //@todo bunch save, update, filtered collection0
}
