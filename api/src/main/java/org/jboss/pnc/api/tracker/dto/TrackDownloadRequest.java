/**
 * Copyright 2026 Red Hat, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package org.jboss.pnc.api.tracker.dto;

import lombok.EqualsAndHashCode;
import lombok.Value;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;

/**
 * Request object for tracking a dependency (downloaded artifact) consumed by the build.
 * <p>
 * Includes additional metadata about the origin of the dependency and the channel through which it was accessed. For
 * artifacts coming from hosted repositories in PNC the origin is expected to be empty.
 * </p>
 */
@Value
@SuperBuilder(toBuilder = true)
@Jacksonized
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
public class TrackDownloadRequest extends TrackedArtifact {

    /**
     * The original remote URL from which the artifact was retrieved. For artifacts coming from hosted repositories in
     * PNC the origin is expected to be empty.
     */
    @EqualsAndHashCode.Include
    String originUrl;
}
