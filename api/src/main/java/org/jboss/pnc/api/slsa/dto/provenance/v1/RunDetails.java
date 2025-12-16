/**
 * JBoss, Home of Professional Open Source.
 * Copyright 2021 Red Hat, Inc., and individual contributors
 * as indicated by the @author tags.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jboss.pnc.api.slsa.dto.provenance.v1;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

@AllArgsConstructor
@Builder
@Jacksonized
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
/**
 * Spec: https://slsa.dev/spec/v1.1/provenance#rundetails
 */
public class RunDetails {

    /**
     * Identifies the build platform that executed the invocation, which is trusted to have correctly performed the
     * operation and populated this provenance.
     */
    private final org.jboss.pnc.api.slsa.dto.provenance.v1.Builder builder;

    /**
     * Metadata about this particular execution of the build.
     */
    private final Metadata metadata;

    /**
     * Additional artifacts generated during the build that are not considered the “output” of the build but that might
     * be needed during debugging or incident response. For example, this might reference logs generated during the
     * build and/or a digest of the fully evaluated build configuration.
     */
    private final List<ResourceDescriptor> byproducts;
}