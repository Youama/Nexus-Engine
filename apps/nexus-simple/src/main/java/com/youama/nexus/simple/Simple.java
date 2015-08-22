package com.youama.nexus.simple;

import com.youama.nexus.core.system.NexusCoreUtil;
import com.youama.nexus.core.system.ServiceUtil;
import com.youama.nexus.scheduler.entity.task.TaskEntity;
import com.youama.nexus.scheduler.entity.task.TaskService;

/**
 * @author David Belicza
 * @since 2015.08.22.
 */
public class Simple {

    public void applicationRunningSimulation() {
        NexusCoreUtil.initServices("nexus-app-simple");

        TaskService service = (TaskService) ServiceUtil.getService(TaskService.class);
        TaskEntity taskEntity = new TaskEntity();
        service.save(taskEntity);
    }
}
