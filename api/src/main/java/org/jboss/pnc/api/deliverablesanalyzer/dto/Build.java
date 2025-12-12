/**
 * JBoss, Home of Professional Open Source.
 * Copyright 2014-2020 Red Hat, Inc., and individual contributors
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
package org.jboss.pnc.api.deliverablesanalyzer.dto;

import java.util.Objects;
import java.util.Set;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import lombok.extern.jackson.Jacksonized;

/**
 *
 * @author jbrazdil
 */
@Data
@Builder(builderClassName = "Builder")
@AllArgsConstructor
@ToString
@Jacksonized
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Build {

    private final BuildSystemType buildSystemType;

    @Positive
    private final Long brewId;

    private final String brewNVR;

    private final String pncId;

    @Valid
    @JsonIgnoreProperties("build")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private final Set<Artifact> artifacts;

    private final boolean isImport;

    public static Build fromPNC(String id, Set<Artifact> artifacts) {
        return fromPNC(id, artifacts, false);
    }

    public static Build fromKoji(long id, String nvr, Set<Artifact> artifacts) {
        return fromKoji(id, nvr, artifacts, false);
    }

    public static Build fromPNC(String id, Set<Artifact> artifacts, boolean isImport) {
        return new Build(BuildSystemType.PNC, null, null, Objects.requireNonNull(id), artifacts, isImport);
    }

    public static Build fromKoji(long id, String nvr, Set<Artifact> artifacts, boolean isImport) {
        return new Build(BuildSystemType.BREW, id, Objects.requireNonNull(nvr), null, artifacts, isImport);
    }
}
