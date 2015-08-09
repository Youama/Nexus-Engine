package com.youama.nexus.scheduler.entity.task;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 * @author David Belicza <87.bdavid@gmail.com>
 * @since 2015.08.02.
 */
public class TaskService implements Task {

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void save(TaskEntity taskEntity) {
        try {
            Session session = this.sessionFactory.openSession();
            Transaction tx = session.beginTransaction();
            session.persist(taskEntity);
            tx.commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
