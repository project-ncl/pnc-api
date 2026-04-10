package org.jboss.pnc.api.bifrost.dto;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class Checksums {
    @NotNull
    private final String md5;

    @NotNull
    private final String sha1;

    @NotNull
    private final String sha256;

    @NotNull
    private final String sha512;
}
