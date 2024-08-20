package com.venus.platform.core.model;

import lombok.Getter;

@Getter
public enum WorkflowStatus {
    PENDING,
    APPROVED,
    REJECTED,
    IN_PROGRESS,
    COMPLETED;

    @Override
    public String toString() {
        return name();
    }
}

