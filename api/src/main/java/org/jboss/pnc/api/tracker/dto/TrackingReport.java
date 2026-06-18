/**
 * Copyright 2026 Red Hat, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package org.jboss.pnc.api.tracker.dto;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

/**
 * The final tracking report for a specific build execution.
 * <p>
 * Encapsulates all metadata for a build, categorized into produced artifacts (uploads)
 * and consumed dependencies (downloads).
 * </p>
 */
@Value
@Jacksonized
@Builder(toBuilder = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class TrackingReport {

    /**
     * The unique identifier for this tracking record.
     */
    private String trackingID;

    /**
     * The set of all artifacts produced by the build.
     */
    private Set<TrackedEntry> uploads;

    /**
     * The set of all dependencies consumed by the build.
     */
    private Set<TrackedEntry> downloads;

}
