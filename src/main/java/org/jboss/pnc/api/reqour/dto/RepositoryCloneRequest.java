/**
 * JBoss, Home of Professional Open Source.
 * Copyright 2024-2024 Red Hat, Inc., and individual contributors
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
package org.jboss.pnc.api.reqour.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;
import org.jboss.pnc.api.reqour.dto.validation.ExternalURL;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * DTO used when cloning the repository
 */
@Value
@Builder
@Jacksonized
@JsonIgnoreProperties(ignoreUnknown = true)
public class RepositoryCloneRequest {

    /**
     * Type of scm: usually: git
     */
    @NotBlank
    String type;

    /**
     * Original repository to sync from
     */
    @ExternalURL
    String originRepoUrl;

    /**
     * Repository to sync to
     */
    @NotBlank
    String targetRepoUrl;

    /**
     * Specific reference to sync. Reference can be a tag, branch, or commit id
     */
    String ref;

    /**
     * Callback specification
     */
    @NotNull
    RepositoryCloneCallback callback;
}
