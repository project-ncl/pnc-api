/**
 * Copyright 2026 Red Hat, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package org.jboss.pnc.api.trackingservice.dto;

import org.jboss.pnc.api.dto.RepositoryId;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.NonFinal;
import lombok.experimental.SuperBuilder;

@Getter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NonFinal
@SuperBuilder(toBuilder = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class TrackedArtifact {

    @EqualsAndHashCode.Include
    private final RepositoryId repoId;

    private final String path;

    @EqualsAndHashCode.Include
    private final String sha256;

    private final String md5;

    private final String sha1;

    private final Long size;

    @EqualsAndHashCode.Include
    @JsonProperty("path")
    public String getPath() {
        if (path == null) {
            return "/";
        }
        return path.startsWith("/") ? path : "/" + path;
    }

}
