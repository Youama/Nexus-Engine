package com.youama.nexus.scheduler.entity.task;

import com.youama.nexus.core.system.Nexus;
import junit.framework.TestCase;
import org.junit.Test;

/**
 * @author David Belicza - 87.bdavid@gmail.com
 * @since 2015.08.08.
 */
public class TaskServiceTest extends TestCase {

    @Test
    public void testSave() {

        TaskService service = (TaskService) Nexus.getService(TaskService.class);
        TaskEntity taskEntity = new TaskEntity();
        service.save(taskEntity);
    }

}