package org.jboss.pnc.api.repositorydriver.dto;

import org.jboss.pnc.api.enums.ResultStatus;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Jacksonized
@lombok.Builder(builderClassName = "Builder")
@JsonIgnoreProperties(ignoreUnknown = true)
public class RepositoryRollbackResult {

    private String buildContentId;
    private ResultStatus status;

}
