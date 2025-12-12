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

import java.util.Collection;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import lombok.Data;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;

/**
 *
 * @author jbrazdil
 */
@Data
@SuperBuilder
@Jacksonized
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.EXISTING_PROPERTY,
        property = "artifactType",
        defaultImpl = Artifact.class)
@JsonSubTypes({
        @JsonSubTypes.Type(value = MavenArtifact.class, name = "MAVEN"),
        @JsonSubTypes.Type(value = NPMArtifact.class, name = "NPM") })
public class Artifact {

    private final BuildSystemType buildSystemType;

    @Positive
    private final Long brewId;

    private final String pncId;

    private final ArtifactType artifactType;

    @NotBlank
    private final String filename;

    private final long size;

    @Pattern(regexp = "^[a-f0-9]{32}$")
    private final String md5;

    @Pattern(regexp = "^[a-f0-9]{40}$")
    private final String sha1;

    @Pattern(regexp = "^[a-f0-9]{64}$")
    private final String sha256;

    private final boolean builtFromSource;

    private final Collection<String> archiveFilenames;

    private final Collection<String> archiveUnmatchedFilenames;

    private final Collection<LicenseInfo> licenses;

    public abstract static class ArtifactBuilder<C extends Artifact, B extends Artifact.ArtifactBuilder<C, B>> {
    }

}
