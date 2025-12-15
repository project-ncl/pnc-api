/*
 * Copyright (C) 2019 Red Hat, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jboss.pnc.api.deliverablesanalyzer.dto;

import java.io.Serializable;
import java.util.List;

import javax.validation.Valid;

import org.jboss.pnc.api.dto.ExceptionResolution;
import org.jboss.pnc.api.enums.ResultStatus;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

/**
 * An object container for the results of the analysis
 *
 * Field errorCause is set only in case of a failed analysis.
 *
 * @author Jakub Bartecek &lt;jbartece@redhat.com&gt;
 */
@AllArgsConstructor
@Data
@Jacksonized
@Builder(builderClassName = "Builder")
@JsonIgnoreProperties(ignoreUnknown = true)
public class AnalysisReport implements Serializable {

    /** Results of the analysis (if analysis was successful) */
    private List<@Valid FinderResult> results;

    /** Enum indicating the resulting analysis status */
    private ResultStatus resultStatus;

    /** Exception log if any caught exception occurs during the process */
    private ExceptionResolution exceptionResolution;

    public AnalysisReport(List<FinderResult> results) {
        this.results = results;
        resultStatus = ResultStatus.SUCCESS;
    }

    public AnalysisReport() {
        resultStatus = ResultStatus.FAILED;
    }

    public static AnalysisReport processWithResolution(
            ResultStatus resultStatus,
            ExceptionResolution exceptionResolution) {
        return AnalysisReport.builder()
                .resultStatus(resultStatus)
                .exceptionResolution(exceptionResolution)
                .build();
    }
}
