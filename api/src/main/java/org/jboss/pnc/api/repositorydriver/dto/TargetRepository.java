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
package org.jboss.pnc.api.repositorydriver.dto;

import javax.validation.constraints.NotNull;

import org.jboss.pnc.api.enums.RepositoryType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.jackson.Jacksonized;

/**
 * Contains information related to a repository of build artifacts (i.e. Maven, NPM, etc).
 *
 * @author Jakub Bartecek &lt;jbartece@redhat.com&gt;
 */
@RequiredArgsConstructor
@Data
@Jacksonized
@Builder(builderClassName = "Builder")
@JsonIgnoreProperties(ignoreUnknown = true)
public class TargetRepository {

    /**
     * Is the reposiotry for temporary builds?
     */
    @NotNull
    private final Boolean temporaryRepo;

    /**
     * Identifier to link repository configurations (eg. hostname)
     */
    @NotNull
    private final String identifier;

    /**
     * The type of repository which hosts this artifact (Maven, NPM, etc). This field determines the format of the
     * identifier string.
     */
    @NotNull
    private final RepositoryType repositoryType;

    /**
     * Path that needs to be appended to the hostname eg. "ga" for https://maven.repository.redhat.com/ga/ or "maven2"
     * for https://repo1.maven.org/maven2/ or "" (empty string) when the repository content starts at root
     */
    @NotNull
    private final String repositoryPath;

}
