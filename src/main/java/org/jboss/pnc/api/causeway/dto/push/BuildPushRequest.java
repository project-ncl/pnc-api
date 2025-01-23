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

import javax.validation.Valid;

import org.jboss.pnc.api.dto.HeartbeatConfig;
import org.jboss.pnc.api.dto.Request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Builder;
import lombok.NonNull;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
@Jacksonized
public class BuildPushRequest {
    @NonNull
    String id;
    @NonNull
    String buildId;
    @NonNull
    String tagPrefix;
    @NonNull
    String username;
    boolean reimport;

    @Valid
    Request callback;

    @Valid
    HeartbeatConfig heartbeat;

}
