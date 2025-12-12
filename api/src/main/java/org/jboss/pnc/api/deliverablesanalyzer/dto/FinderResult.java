package org.jboss.pnc.api.deliverablesanalyzer.dto;

import java.net.URL;
import java.util.Set;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Data
@Jacksonized
@Builder(builderClassName = "Builder")
@JsonIgnoreProperties(ignoreUnknown = true)
public class FinderResult {

    @NotEmpty
    private String id;

    @NotEmpty
    private URL url;

    @NotNull
    @Valid
    private final Set<Build> builds;

    @NotNull
    @Valid
    private final Set<Artifact> notFoundArtifacts;
}
