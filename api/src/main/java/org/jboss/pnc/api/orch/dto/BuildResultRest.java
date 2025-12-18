/**
 * JBoss, Home of Professional Open Source.
 * Copyright 2014-2022 Red Hat, Inc., and individual contributors
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

package org.jboss.pnc.api.orch.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.jboss.pnc.api.enums.orch.CompletionStatus;
import org.jboss.pnc.dto.validation.groups.WhenCreatingNew;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import lombok.*;

/**
 * @author <a href="mailto:matejonnet@gmail.com">Matej Lazar</a>
 */
@Getter
@Setter
@JsonDeserialize(builder = BuildResultRest.Builder.class)
@Builder(builderClassName = "Builder")
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class BuildResultRest implements Serializable {

    @NotNull(groups = WhenCreatingNew.class)
    private CompletionStatus completionStatus;

    private ProcessException processException;

    private BuildExecutionConfigurationRest buildExecutionConfiguration;

    private BuildDriverResultRest buildDriverResult;

    private RepositoryManagerResultRest repositoryManagerResult;

    private EnvironmentDriverResultRest environmentDriverResult;

    private RepourResultRest repourResult;

    @Override
    public String toString() {
        return "BuildResultRest{" + "completionStatus=" + completionStatus + ", processException=" + processException
                + '\'' + ", buildExecutionConfiguration=" + buildExecutionConfiguration + ", buildDriverResult="
                + (buildDriverResult == null ? null : buildDriverResult.toStringLimited())
                + ", repositoryManagerResult="
                + (repositoryManagerResult == null ? null : repositoryManagerResult.toStringLimited())
                + ", environmentDriverResult="
                + (environmentDriverResult == null ? null : environmentDriverResult.toStringLimited())
                + ", repourResult=" + (repourResult == null ? null : repourResult.toStringLimited()) + '}';
    }

}
