package org.jboss.pnc.api.dto;

import javax.validation.constraints.NotNull;

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
    @NotNull
    private OperationResult result;

    /** String representing exception if any */
    private String reason;

    /** String representing resolution proposal if any */
    private String proposal;

    public static OperationOutcome success() {
        return OperationOutcome.builder().result(OperationResult.SUCCESSFUL).build();
    }

    public static OperationOutcome timeout() {
        return OperationOutcome.process(OperationResult.TIMEOUT, null);
    }

    public static OperationOutcome reject() {
        return OperationOutcome.process(OperationResult.REJECTED, null);
    }

    public static OperationOutcome cancel() {
        return OperationOutcome.process(OperationResult.CANCELLED, null);
    }

    public static OperationOutcome fail(ExceptionResolution exceptionResolution) {
        return OperationOutcome.process(OperationResult.FAILED, exceptionResolution);
    }

    public static OperationOutcome systemError(ExceptionResolution exceptionResolution) {
        return OperationOutcome.process(OperationResult.SYSTEM_ERROR, exceptionResolution);
    }

    public static OperationOutcome process(OperationResult operationResult, ExceptionResolution exceptionResolution) {
        if (operationResult == null) {
            throw new IllegalArgumentException(
                    "Invalid operationResult: null. Operation Result is required.");
        }
        if (exceptionResolution == null) {
            return OperationOutcome.builder()
                    .result(operationResult)
                    .build();
        }
        return OperationOutcome.builder()
                .result(operationResult)
                .reason(exceptionResolution.getReason())
                .proposal(exceptionResolution.getProposal())
                .build();
    }
}
