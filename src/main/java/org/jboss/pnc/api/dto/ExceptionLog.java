package org.jboss.pnc.api.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Data
@Jacksonized
@Builder(builderClassName = "Builder")
@JsonIgnoreProperties(ignoreUnknown = true)
public class ExceptionLog {

    /** String representing exception if any */
    private String reason;

    /** String representing resolution proposal if any */
    private String proposal;
}
