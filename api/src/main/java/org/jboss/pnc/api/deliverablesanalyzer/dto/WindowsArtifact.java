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

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;

/**
 *
 * @author dwalluck
 */
@Data
@SuperBuilder
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Jacksonized
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WindowsArtifact extends Artifact {
    @NotBlank
    private final String name;

    @NotBlank
    private final String version;

    @NotBlank
    private final String relPath;

    @NotEmpty
    private final List<String> platforms;

    private final List<String> flags;

    static final class WindowsArtifactBuilderImpl
            extends WindowsArtifactBuilder<WindowsArtifact, WindowsArtifactBuilderImpl> {
        @Override
        public WindowsArtifact build() {
            this.artifactType(ArtifactType.WINDOWS);
            return new WindowsArtifact(this);
        }
    }

    public abstract static class WindowsArtifactBuilder<C extends WindowsArtifact, B extends WindowsArtifact.WindowsArtifactBuilder<C, B>>
            extends ArtifactBuilder<C, B> {
    }
}
