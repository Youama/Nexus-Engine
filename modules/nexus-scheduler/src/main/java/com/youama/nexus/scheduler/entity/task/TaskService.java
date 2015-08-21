package com.youama.nexus.scheduler.entity.task;

import com.youama.nexus.core.singleton.BaseService;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * @author David Belicza - 87.bdavid@gmail.com
 * @since 2015.08.02.
 */
public class TaskService extends BaseService {

    public void save(TaskEntity taskEntity) {
        Session session = null;

        try {
            session = this.sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            session.save(taskEntity);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}
