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
package org.jboss.pnc.api.causeway.dto.push;

import org.jboss.pnc.api.dto.ExceptionResolution;
import org.jboss.pnc.api.enums.ResultStatus;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Data
@Builder
@Jacksonized
@JsonIgnoreProperties(ignoreUnknown = true)
public class PushResult {

    /**
     * ID of pnc build.
     */
    private String buildId;

    /**
     * Build id assigned by Brew.
     */
    private Integer brewBuildId;

    /**
     * Link to the Brew build.
     */
    private String brewBuildUrl;

    /**
     * Result status of the push.
     */
    private ResultStatus result;

    /**
     * Exception log if any caught exception occurs during the process
     */
    private ExceptionResolution exceptionResolution;
}
