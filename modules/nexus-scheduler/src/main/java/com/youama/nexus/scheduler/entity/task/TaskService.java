package com.youama.nexus.scheduler.entity.task;

import com.youama.nexus.core.base.BaseService;

import java.util.List;

/**
 * @author David Belicza
 * @since 2015.08.02.
 */
public class TaskService extends BaseService<TaskModel> {

    public TaskModel getEntityById(long id) {
        return super.getEntityById(TaskModel.class, id);
    }

    public List<TaskModel> getCollection() {
        return super.getCollection(TaskModel.class);
    }
}
