package org.jboss.pnc.api.repositorydriver.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jboss.pnc.api.dto.ComponentVersion;
import org.jboss.pnc.api.repositorydriver.dto.ArchiveRequest;
import org.jboss.pnc.api.repositorydriver.dto.RepositoryCollectRequest;
import org.jboss.pnc.api.repositorydriver.dto.RepositoryCreateRequest;
import org.jboss.pnc.api.repositorydriver.dto.RepositoryCreateResponse;
import org.jboss.pnc.api.repositorydriver.dto.RepositoryPromoteRequest;
import org.jboss.pnc.api.repositorydriver.dto.RepositoryPromoteResult;
import org.jboss.pnc.api.repositorydriver.dto.RepositoryRollbackRequest;
import org.jboss.pnc.api.repositorydriver.dto.RepositoryRollbackResult;

@Path("/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface DriverEndpoint {
    /**
     * Create a new repository for the build. If Indy responds with an error an error response is returned to the
     * invoker.
     */
    @POST
    @Path("/create")
    RepositoryCreateResponse create(RepositoryCreateRequest repositoryCreateRequest);

    /**
     * Seals the tracking report.
     *
     * @param buildContentId
     */
    @PUT
    @Path("/seal")
    void seal(String buildContentId);

    /**
     * Retrieves the tracking report from Indy and promotes the repository. The endpoint returns after tracking report
     * retrieval, if the retrieval fails and error response is returned. The promotion is an async operation, the result
     * is sent via callback defined in the {@link RepositoryPromoteRequest}
     */
    @PUT
    @Path("/promote")
    void promote(RepositoryPromoteRequest promoteRequest);

    /**
     * Rolls back the artifacts promoted by {@link #promote(RepositoryPromoteRequest)}. The operation is synchronous,
     * the outcome is returned in {@link RepositoryRollbackResult#getStatus()}.
     */
    @DELETE
    @Path("/rollback")
    RepositoryRollbackResult rollback(RepositoryRollbackRequest rollbackRequest);

    @POST
    @Path("/archive")
    void archive(ArchiveRequest archiveRequest);

    /**
     * Gets repository manager result for a specific buildContentId. It generates a successful result from tracking
     * report even for builds that failed because of a system error with a sealed tracking record.
     */
    @GET
    @Path("/{id}/repository-manager-result")
    RepositoryPromoteResult collectRepoManagerResult(
            @PathParam("id") String buildContentId,
            RepositoryCollectRequest collectRequest);

    @GET
    @Path("/version")
    ComponentVersion getVersion();
}
