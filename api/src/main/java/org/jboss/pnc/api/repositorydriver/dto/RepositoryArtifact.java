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

package org.jboss.pnc.api.repositorydriver.dto;

import java.time.Instant;

import javax.validation.constraints.NotNull;

import org.jboss.pnc.api.enums.ArtifactQuality;
import org.jboss.pnc.api.enums.BuildCategory;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.jackson.Jacksonized;

/**
 * @author <a href="mailto:matejonnet@gmail.com">Matej Lazar</a>
 */
@RequiredArgsConstructor
@Data
@Jacksonized
@Builder(builderClassName = "Builder")
@JsonIgnoreProperties(ignoreUnknown = true)
public class RepositoryArtifact {

    /**
     * A unique identifier of the artifact in a repository. For example, for a maven artifact this is the GATVC
     * (groupId:artifactId:type:version[:classifier] The format of the identifier string is determined by the repository
     * type.
     */
    @NotNull
    private final String identifier;

    /**
     * A purl is a URL string with format scheme:type/namespace/name@version?qualifiers#subpath useful to reliably
     * reference the same software package using a simple and expressive syntax and conventions based on familiar URLs
     */
    @NotNull
    protected final String purl;

    /**
     * Quality level of the artifact.
     */
    private final ArtifactQuality artifactQuality;

    /**
     * Category of the build denoting its support and usage.
     */
    private final BuildCategory buildCategory;

    /**
     * MD5 checksum of the artifact.
     */
    @NotNull
    private final String md5;

    /**
     * SHA-1 checksum of the artifact.
     */
    @NotNull
    private final String sha1;

    /**
     * SHA-256 checksum of the artifact.
     */
    @NotNull
    private final String sha256;

    /**
     * Filename of the artifact.
     */
    @NotNull
    private final String filename;

    /**
     * Path in the repository where the artifact file is available.
     */
    @NotNull
    private final String deployPath;

    /**
     * The time when this artifact was originally imported. When this artifact was built by PNC the value is null.
     */
    private final Instant importDate;

    /**
     * The location from which this artifact was originally downloaded for import. When this artifact was built by PNC
     * the value is null.
     */
    private final String originUrl;

    /**
     * Size of the artifact in bytes.
     */
    private final Long size;

    /**
     * Repository that stores this artifact.
     */
    private final TargetRepository targetRepository;

}
