package com.youama.nexus.core.entity.version;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * @author David Belicza <87.bdavid@gmail.com>
 * @since 2015.08.02.
 */
public class VersionDAO implements IVersionDAO {

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void save(VersionEntity versionEntity) {
        try {
            Session session = this.sessionFactory.openSession();
            Transaction tx = session.beginTransaction();
            session.persist(versionEntity);
            tx.commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<VersionEntity> getList() {
        Session session = this.sessionFactory.openSession();
        List<VersionEntity> personList = session.createQuery("from Version").list();
        session.close();
        return personList;
    }
}
