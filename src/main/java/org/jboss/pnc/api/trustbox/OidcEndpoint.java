package org.jboss.pnc.api.trustbox;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/oidc")
public interface OidcEndpoint {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("token")
    OidcTokenResponse getOidcToken(OidcTokenRequest oidcTokenRequest);
}
