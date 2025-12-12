package org.jboss.pnc.api.repositorydriver.dto;

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
public class RepositoryCreateResponse {

    private final String repositoryDependencyUrl;

    private final String repositoryDeployUrl;

    private final boolean sidecarEnabled;

    private final boolean sidecarArchiveEnabled;

}
