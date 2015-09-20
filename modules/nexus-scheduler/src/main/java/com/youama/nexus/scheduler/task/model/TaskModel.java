package com.youama.nexus.scheduler.task.model;

import javax.persistence.*;

/**
 * @author David Belicza
 * @since 2015.08.02.
 */
@Entity
@Table(name = TaskTable.TABLE)
public class TaskModel {

    @Id
    @Column(name = TaskTable.COLUMN_ID)
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long taskId;

    @Lob
    @Column(name = TaskTable.COLUMN_PATH, length=2048)
    private String path;

    public void setTaskId(long taskId) {
        this.taskId = taskId;
    }

    public long getTaskId() {
        return taskId;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
