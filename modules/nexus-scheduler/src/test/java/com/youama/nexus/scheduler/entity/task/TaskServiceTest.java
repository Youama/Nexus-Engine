package com.youama.nexus.scheduler.entity.task;

import com.youama.nexus.core.system.NexusCoreUtil;
import com.youama.nexus.core.system.ServiceUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Random;

import static org.junit.Assert.*;

/**
 * @author David Belicza
 * @since 2015.08.08.
 */
public class TaskServiceTest {

    List<String> drivers = ServiceUtil.getInstalledDrivers();

    @Before
    public void setUp() {
        NexusCoreUtil.initServices("nexus-module-scheduler", TaskModel.class);
    }

    @Test
    public void testServiceMethods() {

        long testId = 0L;

        // Add new entities.
        for (String driver : drivers) {
            ServiceUtil.switchDriver(driver);
            TaskService service = (TaskService) ServiceUtil.getService(TaskService.class);
            TaskModel model = new TaskModel();

            testId = service.add(model).getTaskId();
            assertTrue(testId > 0);
        }

        // Get entity
        for (String driver : drivers) {
            ServiceUtil.switchDriver(driver);
            TaskService service = (TaskService) ServiceUtil.getService(TaskService.class);
            TaskModel model = service.getEntityById(testId);
            assertTrue(model != null && model.getTaskId() == testId);
        }

        // Update new entity.
        for (String driver : drivers) {
            ServiceUtil.switchDriver(driver);
            TaskService service = (TaskService) ServiceUtil.getService(TaskService.class);
            TaskModel model = service.getEntityById(testId);

            Random rand = new Random();
            String path = String.valueOf(rand.nextInt(50) + 1);

            model.setPath(path);
            service.update(model);

            assertTrue(path.equals(service.getEntityById(testId).getPath()));
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