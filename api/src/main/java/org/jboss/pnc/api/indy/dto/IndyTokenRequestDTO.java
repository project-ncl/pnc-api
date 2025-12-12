package org.jboss.pnc.api.indy.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

/**
 * DTO of the Indy token endpoint request
 */
@Jacksonized
@Builder
@Data
@AllArgsConstructor
public class IndyTokenRequestDTO {

    @JsonProperty("build-id")
    private String buildId;
}
