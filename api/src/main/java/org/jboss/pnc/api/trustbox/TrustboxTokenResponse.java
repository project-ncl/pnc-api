package org.jboss.pnc.api.trustbox;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;

@Jacksonized
@Builder
@Getter
@Setter
public class TrustboxTokenResponse {
    @JsonProperty("access_token")
    private String accessToken;
}