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

import javax.validation.constraints.NotNull;

import org.jboss.pnc.api.enums.InternalSCMCreationStatus;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

/**
 * Response DTO of internal repository creation operation.
 */
@Builder
@Value
@Jacksonized
@JsonIgnoreProperties(ignoreUnknown = true)
public class InternalSCMCreationResponse {

    /**
     * Git url that you can use to clone anonymously
     */
    String readonlyUrl;

    /**
     * Git url that you can use to clone and push content to the Git repository
     */
    String readwriteUrl;

    /**
     * Status of the repository creation request
     */
    InternalSCMCreationStatus status;

    /**
     * Callback containing information about reqour's async task.
     */
    @NotNull
    ReqourCallback callback;
}
