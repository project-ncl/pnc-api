/**
 * Copyright (C) 2015 Red Hat, Inc. (jbrazdil@redhat.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jboss.pnc.api.causeway.dto.push;

import java.util.Objects;

import org.jboss.pnc.api.dto.Request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NonNull;

/**
 *
 * @author Honza Brázdil &lt;janinko.g@gmail.com&gt;
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class BuildImportRequest {

    @NonNull
    private final Request callback;
    @NonNull
    private final Build build;
    private final boolean reimport;

    private final String userInitiator;

    @JsonCreator
    public BuildImportRequest(
            @JsonProperty("callback") Request callback,
            @JsonProperty("build") Build build,
            @JsonProperty("reimport") Boolean reimport,
            @JsonProperty("userInitiator") String userInitiator) {
        this.callback = Objects.requireNonNull(callback, "Callback must be specified.");
        this.build = Objects.requireNonNull(build, "Build information must be specified.");
        this.reimport = reimport != null && reimport;
        this.userInitiator = userInitiator;
    }
}
