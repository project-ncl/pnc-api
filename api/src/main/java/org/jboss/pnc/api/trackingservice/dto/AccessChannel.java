/**
 * Copyright 2026 Red Hat, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package org.jboss.pnc.api.trackingservice.dto;

/**
 * Enumeration to distinguish between different access channels to stores.
 */
public enum AccessChannel {

    /** Used when the store is accessed via generic http(s) proxy. */
    PROXY,

    /** Used to signify content coming from normal repositories and groups. */
    NATIVE;

}
