package org.jboss.pnc.api.dependencyanalyzer.dto;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.jboss.pnc.api.dependencyanalyzer.enums.Qualifier;

import lombok.Builder;
import lombok.Getter;
import lombok.Singular;
import lombok.ToString;

@Getter
@Builder
@ToString
public class Version {

    private final String version;

    @Singular
    private final Map<Qualifier, Set<String>> qualifiers;

    public Version(String version, Map<Qualifier, Set<String>> qualifiers) {
        this.version = version;
        this.qualifiers = qualifiers;
    }

    public Version(String version) {
        this.version = version;
        this.qualifiers = new HashMap<>();
    }

    public static Version of(String version) {
        return new Version(version);
    }

    public boolean has(Qualifier qualifier, String[] values) {
        String joinedValue = Arrays.stream(values).reduce((val1, val2) -> (val1 + ' ' + val2)).orElse(null);

        return qualifiers.containsKey(qualifier) && qualifiers.get(qualifier).contains(joinedValue);
    }
}
