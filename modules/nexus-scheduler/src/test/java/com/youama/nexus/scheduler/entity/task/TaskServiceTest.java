package com.youama.nexus.scheduler.entity.task;

import com.youama.nexus.core.system.NexusCoreUtil;
import com.youama.nexus.core.system.ServiceUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @author David Belicza - 87.bdavid@gmail.com
 * @since 2015.08.08.
 */
public class TaskServiceTest {

    List<String> drivers = ServiceUtil.getInstalledDrivers();

    @Before
    public void setUp() {
        NexusCoreUtil.initServices("nexus-module-scheduler");
    }

    @Test
    public void testServiceMethods() {

        // Save new entities.
        for (String driver : drivers) {
            ServiceUtil.switchDriver(driver);
            TaskService service = (TaskService) ServiceUtil.getService(TaskService.class);
            TaskModel model = new TaskModel();

            assertTrue(service.save(model) > 0);
        }

        // Get saved entities.
        for (String driver : drivers) {
            ServiceUtil.switchDriver(driver);
            TaskService service = (TaskService) ServiceUtil.getService(TaskService.class);
            List<TaskModel> taskCollection = service.getCollection();

            assertTrue(taskCollection != null && taskCollection.get(0).getTaskId() > 0);
        }
    }

    @After
    public void tierDown() {
        NexusCoreUtil.removeServices();
    }

}