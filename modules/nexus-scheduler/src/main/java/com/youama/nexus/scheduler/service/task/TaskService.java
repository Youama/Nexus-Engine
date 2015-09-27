package com.youama.nexus.scheduler.service.task;

import com.youama.nexus.core.Log;
import com.youama.nexus.core.base.BaseService;
import com.youama.nexus.scheduler.model.task.TaskModel;

import java.util.List;

/**
 * @author David Belicza
 * @since 2015.08.02.
 */
public class TaskService extends BaseService<TaskModel> {

    @Override
    public TaskModel add(TaskModel taskModel) {
        try {
            return super.add(taskModel);
        } catch (Exception e) {
            Log.warning(e);
        }
        
        return null;
    }
    
    @Override
    public TaskModel update(TaskModel taskModel) {
        try {
            return super.update(taskModel);
        } catch (Exception e) {
            Log.warning(e);
        }
        
        return null;
    }
    
    @Override
    public TaskModel save(TaskModel taskModel) {
        try {
            return super.save(taskModel);
        } catch (Exception e) {
            Log.warning(e);
        }
        
        return null;
    }
    
    public TaskModel getEntityById(long id) {
        try {
            return super.getEntityById(TaskModel.class, id);
        } catch (Exception e) {
            Log.warning(e);
        }
        
        return null;
    }

    public List<TaskModel> getCollection() {
        return super.getCollection(TaskModel.class);
    }
}
