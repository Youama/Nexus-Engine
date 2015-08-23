package com.youama.nexus.core.base;

import org.hibernate.SessionFactory;

/**
 * @author David Belicza
 * @since 2015.08.21.
 */
abstract public class BaseService {

    protected SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
