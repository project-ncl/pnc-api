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
 * Spec: https://slsa.dev/spec/v1.1/provenance#builddefinition
 */
public class BuildDefinition {

    /**
     * Identifies the template for how to perform the build and interpret the parameters and dependencies.
     */
    private final String buildType;

    /**
     * The parameters that are under external control, such as those set by a user or tenant of the build platform.
     */
    private final Map<String, Object> externalParameters;

    /**
     * The parameters that are under the control of the entity represented by builder.id. The primary intention of this
     * field is for debugging, incident response, and vulnerability management. The values here MAY be necessary for
     * reproducing the build.
     */
    private final Map<String, Object> internalParameters;

    /**
     * Unordered collection of artifacts needed at build time. Completeness is best effort, at least through SLSA Build
     * L3.
     */
    private final List<ResourceDescriptor> resolvedDependencies;
}