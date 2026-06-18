/**
 * Copyright 2026 Red Hat, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package org.jboss.pnc.api.tracker.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.EqualsAndHashCode;
import lombok.Value;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;

/**
 * A detailed record of a tracked artifact as stored within a {@link TrackingReport}.
 * <p>
 * This class combines basic artifact metadata with specific environmental details
 * like timestamps and local storage URLs. It implements {@link Comparable} to provide
 * a consistent ordering in report listings (by repository, channel, path).
 * </p>
 */
@Value
@SuperBuilder(toBuilder = true)
@Jacksonized
@JsonIgnoreProperties(ignoreUnknown = true)
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
public class TrackedEntry extends TrackedArtifact implements Comparable<TrackedEntry> {

    /**
     * The original remote URL of the artifact. It is {@code null} in case the artifact was downloaded from a local
     * repository or in case of an upload.
     */
    private String originUrl;

    /**
     * The internal/local URL where the artifact is available within the system.
     */
    private String localUrl;

    /**
     * A Unix timestamp representing when this artifact was accessed or recorded. If it is accessed multiple times, only
     * the first occurrence is recorded.
     */
    private Long timestamp;

    @Override
    public int compareTo(final TrackedEntry other) {
        int comp = getRepoId().compareTo(other.getRepoId());
        if (comp == 0) {
            comp = getPath().compareTo(other.getPath());
        }

        return comp;
    }

}
