package org.jboss.pnc.api.trustbox;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;

@Jacksonized
@Builder
@Getter
@Setter
public class TrustboxTokenRequest {

    /**
     * Should be the same format as what quarkus wants it: aka https://{server}/auth/realms/{realm}
     */
    private String authServerUrl;

    /**
     * Client id to authenticate
     */
    private String clientId;

    /**
     * Client secret
     */
    private String clientSecret;
}
