package org.jboss.pnc.api.orch.dto;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.jboss.pnc.dto.validation.groups.WhenImporting;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

@Getter
@Jacksonized
@Builder(builderClassName = "Builder")
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ImportBuildsRequest {

    private final List<@NotNull(groups = WhenImporting.class) BuildImport> imports;
}
