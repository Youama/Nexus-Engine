package com.youama.nexus.core;

import com.youama.nexus.core.entity.version.VersionService;
import com.youama.nexus.core.entity.version.VersionEntity;
import com.youama.nexus.core.system.Nexus;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author David Belicza <87.bdavid@gmail.com>
 * @since 2015.08.02.
 */
public class Boot {

    public Boot() {

        Nexus.initiateSystem();
        VersionService service = (VersionService) Nexus.getService(VersionService.class);
        VersionEntity versionEntity = new VersionEntity();
        service.save(versionEntity);

        /*String[] locations = new String[1];
        locations[0] = "ResourceEntity.xml";
        ApplicationContext context = new ClassPathXmlApplicationContext(locations);
        VersionService versionDAO = (VersionService) context.getBean("coreVersion");
        /*Configuration configuration = new Configuration();
        configuration.configure();*/
        //Configuration configuration = new Configuration().configure("database/Session.xml");

        /*.buildSessionFactory();
        versionDAO.setSessionFactory(sessionFactory);*/
        /*
        VersionEntity versionEntity = new VersionEntity();
        versionEntity.setVersionKey("Pankaj");

        versionDAO.save(versionEntity);*/
    }
}
