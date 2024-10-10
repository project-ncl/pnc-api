/**
 * Copyright 2024 Red Hat, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package org.jboss.pnc.api.reqour.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;
import org.jboss.pnc.api.reqour.dto.validation.ValidGitRepositoryURL;

/**
 * TODO
 */
@Builder
@Value
@Jacksonized
@JsonIgnoreProperties(ignoreUnknown = true)
public class InternalGitRepositoryUrl {

    /**
     * Git repository URL that you can use to clone anonymously
     */
    @ValidGitRepositoryURL
    String readonlyUrl;

    /**
     * Git repository URL that you can use for pushing of the content
     */
    @ValidGitRepositoryURL
    String readwriteUrl;
}
