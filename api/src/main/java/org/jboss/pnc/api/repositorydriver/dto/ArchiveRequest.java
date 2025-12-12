package org.jboss.pnc.api.repositorydriver.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

@AllArgsConstructor
@Builder
@Jacksonized
@Getter
public class ArchiveRequest {
    private final String buildContentId;
    private final String buildConfigId;
}
