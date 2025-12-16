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
import com.fasterxml.jackson.annotation.JsonProperty;

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
 * Spec: https://github.com/in-toto/attestation/blob/7aefca35a0f74a6e0cb397a8c4a76558f54de571/spec/v1/statement.md
 */
public class Provenance {

    /**
     * Identifier for the schema of the Statement. Always https://in-toto.io/Statement/v1 for this version of the spec.
     */
    @JsonProperty("_type")
    private final String type;

    /**
     * Set of software artifacts that the attestation applies to. Each element represents a single software artifact.
     * Each element MUST have digest set.
     */
    private final List<ResourceDescriptor> subject;

    /**
     * URI identifying the type of the Predicate.
     */
    private final String predicateType;

    /**
     * Additional parameters of the Predicate. Unset is treated the same as set-but-empty. MAY be omitted if
     * predicateType fully describes the predicate.
     */
    private final Predicate predicate;
}