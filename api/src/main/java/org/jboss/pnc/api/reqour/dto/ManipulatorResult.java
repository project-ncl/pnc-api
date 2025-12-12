/**
 * Copyright 2024 Red Hat, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package org.jboss.pnc.api.reqour.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

/**
 * Result of the manipulator operation.<br/>
 * This is already a unified format returned by reqour for any kind of adjustment type.<br/>
 * In other words, the result of manipulation will be of this format for any adjust type (MVN, GRADLE). However, the
 * underlying results returned from corresponding manipulators (PME, GME) might differ.
 */
@Builder
@Value
@Jacksonized
@JsonIgnoreProperties(ignoreUnknown = true)
public class ManipulatorResult {

    VersioningState versioningState;

    List<RemovedRepository> removedRepositories;
}
