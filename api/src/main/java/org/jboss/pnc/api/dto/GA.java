/**
 * Copyright 2024 Red Hat, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package org.jboss.pnc.api.dto;

import java.util.regex.Pattern;

import javax.validation.constraints.NotNull;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

/**
 * Group ID and Artifact ID of a GAV.
 */
@Builder
@Value
@Jacksonized
public class GA {

    public static final Pattern GROUP_ID_PATTERN = Pattern.compile("(\\w[\\w-]*\\.)*\\w[\\w-]*");

    public static final Pattern ARTIFACT_ID_PATTERN = Pattern.compile("[a-zA-Z0-9_.-]+");

    @NotNull
    String groupId;

    @NotNull
    String artifactId;

    @Override
    public String toString() {
        return groupId + ":" + artifactId;
    }

    public boolean isValid() {
        return GROUP_ID_PATTERN.matcher(groupId).matches() && ARTIFACT_ID_PATTERN.matcher(artifactId).matches();
    }
}
