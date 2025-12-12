package org.jboss.pnc.api.dto;

import org.jboss.pnc.api.enums.OperationResult;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Data
@Jacksonized
@Builder(builderClassName = "Builder")
@JsonIgnoreProperties(ignoreUnknown = true)
public class OperationOutcome {

    /** Flag indicating status of the operation */
    private OperationResult result;

    /** String representing exception if any */
    private String reason;

    /** String representing resolution proposal if any */
    private String proposal;

    public static OperationOutcome success() {
        return OperationOutcome.builder().result(OperationResult.SUCCESSFUL).build();
    }

    public static OperationOutcome fail() {
        return OperationOutcome.builder().result(OperationResult.FAILED).build();
    }

    public static OperationOutcome process(OperationResult operationResult, ExceptionResolution exceptionResolution) {
        return OperationOutcome.builder()
                .result(operationResult)
                .reason(exceptionResolution.getReason())
                .proposal(exceptionResolution.getProposal())
                .build();
    }
}
