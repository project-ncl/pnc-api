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

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;

/**
 *
 * @author jbrazdil
 */
@Data
@SuperBuilder
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Jacksonized
public class MavenArtifact extends Artifact {

    @NotBlank
    private final String groupId;

    @NotBlank
    private final String artifactId;

    private final String type;

    @NotBlank
    private final String version;

    private final String classifier;

    static final class MavenArtifactBuilderImpl extends MavenArtifactBuilder<MavenArtifact, MavenArtifactBuilderImpl> {

        @Override
        public MavenArtifact build() {
            this.artifactType(ArtifactType.MAVEN);
            return new MavenArtifact(this);
        }
    }

    public abstract static class MavenArtifactBuilder<C extends MavenArtifact, B extends MavenArtifact.MavenArtifactBuilder<C, B>>
            extends ArtifactBuilder<C, B> {
    }
}
