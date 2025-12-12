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

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Data;
import lombok.ToString;

/**
 * Request DTO to create an internal scm via Repour. The endpoint is POST /internal-scm
 */
@Data
@Builder(builderClassName = "Builder")
@AllArgsConstructor
@ToString(callSuper = true)
@JsonDeserialize(builder = InternalScmCreationRequest.Builder.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class InternalScmCreationRequest {

    /**
     * Project name. Can be in format 'group/project'. Don't include the '.git' in the end of the string
     */
    private String project;

    /**
     * Parent project from which the project gets its permissions
     */
    @JsonProperty("parent_project")
    private String parentProject;

    /**
     * Owner groups on who own that repository
     */
    @JsonProperty("owner_groups")
    @Default
    private List<String> ownerGroups = new ArrayList<>();

    /**
     * Description on the project
     */
    private String description;

    @JsonPOJOBuilder(withPrefix = "")
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static final class Builder {
    }
}
