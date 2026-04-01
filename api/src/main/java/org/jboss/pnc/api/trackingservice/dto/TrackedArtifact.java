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

/**
 * Base class for artifact metadata within the tracking system.
 * <p>
 * Contains common attributes for any file involved in a build process, such as storage location (repository ID and
 * path), checksums for integrity verification, and file size.
 * </p>
 */
@Getter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NonFinal
@SuperBuilder(toBuilder = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class TrackedArtifact {

    /**
     * The identifier of the repository where the artifact is located.
     */
    @EqualsAndHashCode.Include
    private final RepositoryId repoId;

    /**
     * The package type of this artifact.
     */
    private final PackageType packageType;

    private final String path;

    @EqualsAndHashCode.Include
    private final String sha256;

    private final String md5;

    private final String sha1;

    private final Long size;

    /**
     * Returns the normalized path of the artifact.
     * <p>
     * Ensures the path always starts with a leading forward slash.
     * </p>
     *
     * @return The normalized absolute path.
     */
    @EqualsAndHashCode.Include
    @JsonProperty("path")
    public String getPath() {
        if (path == null) {
            return "/";
        }
        return path.startsWith("/") ? path : "/" + path;
    }

}
