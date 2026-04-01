/**
 * Copyright 2026 Red Hat, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package org.jboss.pnc.api.trackingservice.dto;

/**
 * Enumeration to distinguish between different package types. It can be either mvn, npm or generic.
 */
public enum PackageType {

    MVN,
    NPM,
    GENERIC;

}
