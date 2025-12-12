package org.jboss.pnc.api.indy.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

/**
 * DTO of the Indy token endpoint response
 */
@Jacksonized
@Builder
@Data
@AllArgsConstructor
public class IndyTokenResponseDTO {

    private String token;
}
