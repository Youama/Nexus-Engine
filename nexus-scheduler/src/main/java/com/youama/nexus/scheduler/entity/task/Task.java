package com.youama.nexus.scheduler.entity.task;

import org.hibernate.SessionFactory;

/**
 * @author David Belicza <87.bdavid@gmail.com>
 * @since 2015.08.02.
 */
public interface Task {

    void setSessionFactory(SessionFactory sessionFactory);
    void save(TaskEntity taskEntity);
}
