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
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

@AllArgsConstructor
@lombok.Builder(builderClassName = "BuilderDTOBuilder", builderMethodName = "newBuilder")
@Jacksonized
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
/**
 * Spec: https://slsa.dev/spec/v1.1/provenance#builder
 */
public class Builder {

    /**
     * URI indicating the transitive closure of the trusted build platform. This is intended to be the sole determiner
     * of the SLSA Build level.
     */
    private final String id;

    /**
     * Map of names of components of the build platform to their version.
     */
    private final Map<String, String> version;

    /**
     * Dependencies used by the orchestrator that are not run within the workload and that do not affect the build, but
     * might affect the provenance generation or security guarantees.
     */
    private final List<ResourceDescriptor> builderDependencies;
}