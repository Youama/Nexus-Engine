package com.youama.nexus.core;

import com.youama.nexus.core.entity.version.VersionDAO;
import com.youama.nexus.core.entity.version.VersionEntity;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author David Belicza <87.bdavid@gmail.com>
 * @since 2015.08.02.
 */
public class Boot {

    public Boot() {
        ApplicationContext context = new ClassPathXmlApplicationContext("ResourceEntity.xml");
        VersionDAO versionDAO = (VersionDAO) context.getBean("versionDAO2");
        VersionEntity versionEntity = new VersionEntity();
        versionEntity.setVersionKey("Pankaj");

        versionDAO.save(versionEntity);
    }
}
