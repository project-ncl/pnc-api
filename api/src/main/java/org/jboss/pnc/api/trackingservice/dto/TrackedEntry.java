/**
 * Copyright 2026 Red Hat, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package org.jboss.pnc.api.trackingservice.dto;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.EqualsAndHashCode;
import lombok.Value;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;

@Value
@SuperBuilder(toBuilder = true)
@Jacksonized
@JsonIgnoreProperties(ignoreUnknown = true)
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
public class TrackedEntry extends TrackedArtifact implements Comparable<TrackedEntry> {

    private String originUrl;

    private AccessChannel accessChannel;

    private String localUrl;

    private Set<Long> timestamps;

    @Override
    public int compareTo(final TrackedEntry other) {
        int comp = getRepoId().compareTo(other.getRepoId());
        if (comp == 0) {
            comp = getAccessChannel().compareTo(other.getAccessChannel());
        }
        if (comp == 0) {
            comp = getPath().compareTo(other.getPath());
        }

        return comp;
    }

}
