package org.jboss.pnc.api.repositorydriver.dto;

import org.jboss.pnc.api.enums.BuildCategory;

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
public class RepositoryCollectRequest {

    private final BuildCategory buildCategory;
    private final boolean tempBuild;

}
