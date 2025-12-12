/**
 * Copyright 2024 Red Hat, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package org.jboss.pnc.api.reqour.dto;

import java.net.URI;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

/**
 * Removed repository
 */
@Builder
@Value
@Jacksonized
@JsonIgnoreProperties(ignoreUnknown = true)
public class RemovedRepository {

    /**
     *
     * Boolean flag whether released versions are allowed to be read from the repository
     */
    boolean releases;

    /**
     * Boolean flag whether snapshots versions are allowed to be read from the repository
     */
    boolean snapshots;

    /**
     * Name of the removed repository
     */
    String name;

    /**
     * ID of the removed repository
     */
    String id;

    /**
     * URL of the removed repository.<br/>
     * Has to be a {@link String} (and not e.g. {@link URI}), see NCL-9084 for further info.
     */
    String url;
}
