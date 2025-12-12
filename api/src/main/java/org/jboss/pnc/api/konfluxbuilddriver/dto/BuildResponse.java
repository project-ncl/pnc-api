package org.jboss.pnc.api.konfluxbuilddriver.dto;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.jackson.Jacksonized;

@RequiredArgsConstructor
@Data
@Jacksonized
@Builder(builderClassName = "Builder")
public class BuildResponse {
    private final String pipelineId;
    private final String namespace;
}
