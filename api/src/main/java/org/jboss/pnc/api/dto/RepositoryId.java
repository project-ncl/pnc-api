package org.jboss.pnc.api.dto;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

/**
 * Repository identification in Artifactory.
 */
@Builder
@Value
@Jacksonized
public class RepositoryId implements Comparable<RepositoryId> {

    private String project;

    private String name;

    /**
     * Generates path element in Artifactory for artifacts retrieval.
     *
     * @return generated path element
     */
    public String getPath() {
        return project + "-" + name;
    }

    @Override
    public int compareTo(RepositoryId o) {
        return getPath().compareTo(o.getPath());
    }

}