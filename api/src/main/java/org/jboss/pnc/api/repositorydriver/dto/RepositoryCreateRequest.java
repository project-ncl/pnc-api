package org.jboss.pnc.api.repositorydriver.dto;

import java.util.List;

import org.jboss.pnc.api.enums.BuildCategory;
import org.jboss.pnc.api.enums.BuildType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.jackson.Jacksonized;

/**
 * @author <a href="mailto:matejonnet@gmail.com">Matej Lazar</a>
 */
@RequiredArgsConstructor
@Data
@Jacksonized
@lombok.Builder(builderClassName = "Builder")
@JsonIgnoreProperties(ignoreUnknown = true)
public class RepositoryCreateRequest {

    private final String buildContentId;
    private final BuildType buildType;
    private final BuildCategory buildCategory;
    private final boolean tempBuild;

    private final boolean brewPullActive;
    private final List<String> extraRepositories;

}
