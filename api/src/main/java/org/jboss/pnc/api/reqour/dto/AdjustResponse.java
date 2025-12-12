/**
 * Copyright 2024 Red Hat, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package org.jboss.pnc.api.reqour.dto;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

/**
 * Response DTO of the alignment operation
 */
@Builder
@Value
@Jacksonized
@JsonIgnoreProperties(ignoreUnknown = true)
public class AdjustResponse {

    /**
     * Tag which was pushed. It is a tag over {@link AdjustResponse#getDownstreamCommit()}.
     */
    String tag;

    /**
     * The commit ID which contains alignment changes
     */
    String downstreamCommit;

    /**
     * Git repository internal URL used for cloning and pushing
     */
    InternalGitRepositoryUrl internalUrl;

    /**
     * The commit ID over which we are doing the alignment
     */
    String upstreamCommit;

    /**
     * Boolean flag whether {@link AdjustRequest#getRef()} provided in the request is a reference present in any
     * internal SCM repository.
     */
    boolean isRefRevisionInternal;

    /**
     * Result of the manipulation operation.<br/>
     */
    ManipulatorResult manipulatorResult;

    /**
     * Callback containing information about Reqour's async task.
     */
    @NotNull
    ReqourCallback callback;
}
