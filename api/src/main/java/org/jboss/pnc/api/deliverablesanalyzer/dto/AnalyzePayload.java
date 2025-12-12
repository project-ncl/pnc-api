/*
 * Copyright (C) 2019 Red Hat, Inc.
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
package org.jboss.pnc.api.deliverablesanalyzer.dto;

import java.io.Serializable;
import java.util.List;

import org.jboss.pnc.api.dto.HeartbeatConfig;
import org.jboss.pnc.api.dto.Request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.jackson.Jacksonized;

@ToString
@Getter
@AllArgsConstructor
@Jacksonized
@Builder
public class AnalyzePayload implements Serializable {

    private static final long serialVersionUID = -2043312712105992478L;

    private String operationId;

    private List<String> urls;

    private String config;

    private Request callback;

    private HeartbeatConfig heartbeat;
}
