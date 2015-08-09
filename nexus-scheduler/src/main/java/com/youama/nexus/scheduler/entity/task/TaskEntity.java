package com.youama.nexus.scheduler.entity.task;

import javax.persistence.*;

/**
 * @author David Belicza <87.bdavid@gmail.com>
 * @since 2015.08.02.
 */

@Entity
@Table(name = "Tasks")
public class TaskEntity {

    @Id
    @Column(name="taskId")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long taskId;

    public void setTaskId(long taskId) {
        this.taskId = taskId;
    }

    public long getTaskId() {
        return taskId;
    }
}
