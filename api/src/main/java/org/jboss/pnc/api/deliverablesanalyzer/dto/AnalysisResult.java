package org.jboss.pnc.api.deliverablesanalyzer.dto;

import java.util.List;

import org.jboss.pnc.api.dto.Request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import lombok.extern.jackson.Jacksonized;

@Data
@Jacksonized
@Builder(builderClassName = "Builder")
@JsonIgnoreProperties(ignoreUnknown = true)
public class AnalysisResult {

    @NonNull
    private final String operationId;

    @NonNull
    private final List<FinderResult> results;

    private final boolean scratch;

    @NonNull
    private final Request callback;
}
