package org.jboss.pnc.api.konfluxbuilddriver.dto;

/**
 * The Konflux pipeline status, based on:
 * https://github.com/tektoncd/pipeline/blob/main/docs/pipelines.md#using-aggregate-execution-status-of-all-tasks
 */
public enum PipelineStatus {
    /**
     * all tasks have succeeded
     **/
    Succeeded,

    /**
     * one ore more tasks failed
     */
    Failed,

    /**
     * all tasks completed successfully including one or more skipped tasks
     */
    Completed,

    /**
     * no aggregate execution status available (i.e. none of the above), one or more tasks could be
     * pending/running/cancelled/timedout
     */
    None
}
