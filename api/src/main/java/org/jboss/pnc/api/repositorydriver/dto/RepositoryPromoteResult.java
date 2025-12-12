package org.jboss.pnc.api.repositorydriver.dto;

import java.util.Collections;
import java.util.List;

import org.jboss.pnc.api.enums.ResultStatus;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.jackson.Jacksonized;

@RequiredArgsConstructor
@Data
@Jacksonized
@Builder(builderClassName = "Builder")
@JsonIgnoreProperties(ignoreUnknown = true)
public class RepositoryPromoteResult {

    private final List<RepositoryArtifact> builtArtifacts;
    private final List<RepositoryArtifact> dependencies;
    private final String buildContentId;
    private final ResultStatus status;

    public static RepositoryPromoteResult failed(String buildContentId, ResultStatus status) {
        return new RepositoryPromoteResult(Collections.emptyList(), Collections.emptyList(), buildContentId, status);
    }
}
