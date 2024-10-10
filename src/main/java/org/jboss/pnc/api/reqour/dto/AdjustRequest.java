/**
 * Copyright 2024 Red Hat, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package org.jboss.pnc.api.reqour.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;
import org.jboss.pnc.api.constants.BuildConfigurationParameterKeys;
import org.jboss.pnc.api.dto.Request;
import org.jboss.pnc.api.enums.AlignmentPreference;
import org.jboss.pnc.api.enums.BuildType;
import org.jboss.pnc.api.reqour.dto.validation.ValidGitRepositoryURL;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Map;

/**
 * Request DTO used when doing the build alignment.
 */
@Builder
@Value
@Jacksonized
@JsonIgnoreProperties(ignoreUnknown = true)
public class AdjustRequest {

    /**
     * Git repository internal URL used for cloning and pushing
     */
    @NotNull
    InternalGitRepositoryUrl internalUrl;

    /**
     * Specific reference to align. Reference can be a tag, branch, or commit id
     */
    String ref;

    /**
     * Callback specification
     */
    @NotNull
    Request callback;

    /**
     * Is pre-build sync enabled?
     */
    boolean sync;

    /**
     * Git repository URL of the origin
     */
    @ValidGitRepositoryURL
    String originRepoUrl;

    /**
     * User-specified build parameters from build configuration. These contain for instance
     * {@link org.jboss.pnc.api.constants.BuildConfigurationParameterKeys#ALIGNMENT_PARAMETERS} parameters, which are
     * used during alignment phase.
     */
    Map<BuildConfigurationParameterKeys, String> buildConfigParameters;

    /**
     * Is the build temporary?
     */
    boolean tempBuild;

    /**
     * Build dependencies alignment preference
     */
    AlignmentPreference alignmentPreference;

    /**
     * Build type
     */
    @NotNull
    BuildType buildType;

    /**
     * Default alignment parameters set by PNC. Users can of course override these sane defaults, e.g. by using
     * {@link org.jboss.pnc.api.constants.BuildConfigurationParameterKeys#ALIGNMENT_PARAMETERS}.
     */
    String pncDefaultAlignmentParameters;

    /**
     * Is pulling from brew enabled?
     */
    boolean brewPullActive;

    /**
     * Task ID which is assigned to the asynchronous execution of the request
     */
    @NotBlank
    String taskId;
}
