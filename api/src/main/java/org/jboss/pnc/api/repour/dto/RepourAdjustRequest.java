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
package org.jboss.pnc.api.repour.dto;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

/**
 * DTO to run Repour's adjust request for build alignment
 */
@Data
@Builder(builderClassName = "Builder")
@AllArgsConstructor
@ToString(callSuper = true)
@JsonDeserialize(builder = RepourAdjustRequest.Builder.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class RepourAdjustRequest {

    @JsonProperty("internal_url")
    private RepourAdjustInternalUrl internalUrl;
    private String ref;

    private RepourAdjustCallback callback;

    private boolean sync;
    private String originRepoUrl;
    private Map<String, String> adjustParameters;

    private boolean tempBuild;
    private String tempBuildTimestamp;
    private String alignmentPreference;
    private String taskId;

    private String buildType;
    private String defaultAlignmentParams;
    private boolean brewPullActive;

    @JsonPOJOBuilder(withPrefix = "")
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static final class Builder {
    }
}
