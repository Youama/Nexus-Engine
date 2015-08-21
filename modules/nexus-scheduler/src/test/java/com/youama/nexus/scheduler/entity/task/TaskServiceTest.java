package com.youama.nexus.scheduler.entity.task;

import com.youama.nexus.core.system.NexusCoreUtil;
import com.youama.nexus.core.system.ServiceUtil;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * @author David Belicza - 87.bdavid@gmail.com
 * @since 2015.08.08.
 */
public class TaskServiceTest {

    List<String> drivers = ServiceUtil.getInstalledDrivers();

    @Before
    public void setUp() {
        NexusCoreUtil.initServices();
    }

    @Test
    public void testSave() {
        for (String driver : drivers) {
            ServiceUtil.switchDriver(driver);
            TaskService service = (TaskService) ServiceUtil.getService(TaskService.class);
            TaskEntity taskEntity = new TaskEntity();
            service.save(taskEntity);
        }
    }

}