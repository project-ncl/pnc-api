package org.jboss.pnc.api.konfluxbuilddriver.dto;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.jackson.Jacksonized;

@RequiredArgsConstructor
@Data
@Jacksonized
@Builder(builderClassName = "Builder")
public class BuildCompleted {
    private final PipelineStatus status;
    private final String buildId;
}
