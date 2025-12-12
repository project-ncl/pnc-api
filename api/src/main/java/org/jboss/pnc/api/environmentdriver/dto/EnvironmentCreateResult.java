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

package org.jboss.pnc.api.environmentdriver.dto;

import java.net.URI;

import org.jboss.pnc.api.enums.ResultStatus;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.jackson.Jacksonized;

/**
 * @author <a href="mailto:matejonnet@gmail.com">Matej Lazar</a>
 */
@RequiredArgsConstructor
@Data
@Jacksonized
@Builder(builderClassName = "Builder")
@JsonIgnoreProperties(ignoreUnknown = true)
public class EnvironmentCreateResult {

    private final URI environmentBaseUri;
    private final String workingDirectory;
    private final String sshPassword;
    private final ResultStatus status;

    public static EnvironmentCreateResult cancelled() {
        return EnvironmentCreateResult.builder().status(ResultStatus.CANCELLED).build();
    }

    public static EnvironmentCreateResult failed(Throwable throwable) {
        return EnvironmentCreateResult.builder().status(ResultStatus.FAILED).build();
    }

    public static EnvironmentCreateResult systemError(Throwable throwable) {
        return EnvironmentCreateResult.builder().status(ResultStatus.SYSTEM_ERROR).build();
    }

    public static EnvironmentCreateResult success(URI environmentBaseUri, String workingDirectory, String sshPassword) {
        return EnvironmentCreateResult.builder()
                .status(ResultStatus.SUCCESS)
                .environmentBaseUri(environmentBaseUri)
                .workingDirectory(workingDirectory)
                .sshPassword(sshPassword)
                .build();
    }

}
