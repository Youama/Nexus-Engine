package com.youama.nexus.scheduler.entity.task;

import javax.persistence.*;

/**
 * @author David Belicza - 87.bdavid@gmail.com
 * @since 2015.08.02.
 */

@Entity
@Table(name = "Task")
public class TaskModel {

    @Id
    @Column(name="taskId")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long taskId;

    @Lob
    @Column(name="path", length=2048)
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
