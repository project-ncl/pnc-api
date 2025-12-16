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
 * Spec:
 * https://github.com/in-toto/attestation/blob/7aefca35a0f74a6e0cb397a8c4a76558f54de571/spec/v1/resource_descriptor.md
 */
public class ResourceDescriptor {

    /**
     * Machine-readable identifier for distinguishing between descriptors.
     */
    private final String name;

    /**
     * A set of cryptographic digests of the contents of the resource or artifact. This field is REQUIRED unless either
     * uri or content is set.
     */
    private final Map<String, String> digest;

    /**
     * A URI used to identify the resource or artifact globally. This field is REQUIRED unless either digest or content
     * is set.
     */
    private final String uri;

    /**
     * The location of the described resource or artifact, if different from the uri.
     */
    private final String downloadLocation;

    /**
     * The MIME Type (i.e., media type) of the described resource or artifact.
     */
    private final String mediaType;

    /**
     * The contents of the resource or artifact. This field is REQUIRED unless either uri or digest is set.
     */
    private final String content;

    /**
     * This field MAY be used to provide additional information or metadata about the resource or artifact that may be
     * useful to the consumer when evaluating the attestation against a policy.
     */
    private final Map<String, Object> annotations;
}