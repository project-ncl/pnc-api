/**
 * Copyright 2024 Red Hat, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package org.jboss.pnc.api.reqour.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;
import org.jboss.pnc.api.reqour.dto.validation.GitCommitHash;

/**
 * Response DTO of the build alignment operation
 */
@Builder
@Value
@Jacksonized
@JsonIgnoreProperties(ignoreUnknown = true)
public class AdjustResponse {

    // TODO: Complete javadocs once second half is done too

    String tag;

    @GitCommitHash
    String downstreamCommit;

    InternalGitRepositoryUrl internalUrl;

    @GitCommitHash
    String upstreamCommit;

    boolean isRefRevisionInternal;

    // FIXME: Change from Object to more appropriate object, once doing the second half
    Object adjustResultData;

    ReqourCallback callback;
}
