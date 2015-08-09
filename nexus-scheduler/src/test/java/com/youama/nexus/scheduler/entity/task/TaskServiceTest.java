package com.youama.nexus.scheduler.entity.task;

import com.youama.nexus.core.entity.version.VersionEntity;
import com.youama.nexus.core.entity.version.VersionService;
import com.youama.nexus.core.system.Nexus;
import junit.framework.TestCase;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author David Belicza <87.bdavid@gmail.com>
 * @since 2015.08.08.
 */
public class TaskServiceTest extends TestCase {

    @Test
    public void testSave() {

        Nexus.initiateSystem();
        VersionService service = (VersionService) Nexus.getService(VersionService.class);
        VersionEntity versionEntity = new VersionEntity();
        service.save(versionEntity);

        /*Nexus.initiateSystem();
        TaskService service = (TaskService) Nexus.getService(TaskService.class);
        TaskEntity taskEntity = new TaskEntity();
        service.save(taskEntity);*/

        /*
        ApplicationContext context = new ClassPathXmlApplicationContext("ResourceEntity.xml");
        Task task = (Task) context.getBean("schedulerTask");

        TaskEntity taskEntity = new TaskEntity();

        task.save(taskEntity);
        */
    }

}