package org.jboss.pnc.api.konfluxbuilddriver.dto;

import org.jboss.pnc.api.dto.Request;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.jackson.Jacksonized;

@RequiredArgsConstructor
@Data
@Jacksonized
@Builder(builderClassName = "Builder")
public class BuildRequest {
    private final String recipeImage;
    private final String buildTool;
    private final String buildToolVersion;
    private final String javaVersion;
    private final String projectName;
    private final String scmUrl;
    private final String scmRevision;
    private final String buildScript;
    private final String repositoryDependencyUrl;
    private final String repositoryDeployUrl;
    private final String repositoryBuildContentId;
    private final String namespace;
    private final String podMemoryOverride;
    // Callback to use for the completion notification
    private final Request completionCallback;
}
