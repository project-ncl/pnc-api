/**
 * Copyright 2024 Red Hat, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package org.jboss.pnc.api.dto;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

/**
 * GAV identifier of the artifact.
 */
@Builder
@Value
@Jacksonized
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonIncludeProperties({ "groupId", "artifactId", "version" })
@JsonPropertyOrder({ "groupId", "artifactId", "version" })
public class GAV {

    @JsonUnwrapped
    @NotNull
    GA ga;

    @NotNull
    String version;

    public String getGroupId() {
        return ga.getGroupId();
    }

    public String getArtifactId() {
        return ga.getArtifactId();
    }

    @Override
    public String toString() {
        return ga + ":" + version;
    }
}
