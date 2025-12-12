package org.jboss.pnc.api.repositorydriver.dto;

import org.jboss.pnc.api.dto.Request;
import org.jboss.pnc.api.enums.BuildCategory;
import org.jboss.pnc.api.enums.BuildType;

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
public class RepositoryPromoteRequest {

    private final String buildContentId;
    private final BuildType buildType;
    private final boolean tempBuild;
    private final BuildCategory buildCategory;
    private final Request callback;
    private final Request heartBeat;
    private final String buildConfigurationId;
}
