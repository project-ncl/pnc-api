package org.jboss.pnc.api.causeway.dto.push;

import java.util.Date;
import java.util.Objects;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonTypeName;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.extern.jackson.Jacksonized;

/**
 * @author <a href="mailto:matejonnet@gmail.com">Matej Lazar</a>
 */
@Data
@EqualsAndHashCode(callSuper = true)
@JsonTypeName(value = "npm")
@JsonIgnoreProperties(ignoreUnknown = true)
public class NpmBuild extends Build {

    @NonNull
    private final String name;

    private final String version;

    @Jacksonized
    @Builder
    private NpmBuild(
            String name,
            String version,
            String buildName,
            String buildVersion,
            String externalBuildSystem,
            String externalBuildID,
            String externalBuildURL,
            Date startTime,
            Date endTime,
            String scmURL,
            String scmRevision,
            String scmTag,
            BuildRoot buildRoot,
            Set<Logfile> logs,
            String sourcesURL,
            Set<Dependency> dependencies,
            Set<BuiltArtifact> builtArtifacts,
            String tagPrefix) {
        super(
                buildName,
                buildVersion,
                externalBuildSystem,
                externalBuildID,
                externalBuildURL,
                startTime,
                endTime,
                scmURL,
                scmRevision,
                scmTag,
                buildRoot,
                logs,
                sourcesURL,
                dependencies,
                builtArtifacts,
                tagPrefix);
        this.name = Objects.requireNonNull(name);
        this.version = version;
    }
}
