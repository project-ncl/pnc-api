/**
 * Copyright 2026 Red Hat, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package org.jboss.pnc.api.trackingservice.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.jboss.pnc.api.trackingservice.dto.TrackDownloadRequest;
import org.jboss.pnc.api.trackingservice.dto.TrackUploadRequest;
import org.jboss.pnc.api.trackingservice.dto.TrackingReport;

/**
 * Service endpoint for managing build tracking reports.
 * <p>
 * This API facilitates the lifecycle of build metadata tracking, allowing systems to record produced artifacts
 * (uploads) and consumed dependencies (downloads). Reports can be initialized, retrieved, and finally sealed to ensure
 * the immutability of the build record. It also allows retrieval of stored tracking data.
 * </p>
 * <p>
 * All operations are centered around a unique {@code trackingId} provided by the client, corresponding to a specific
 * PNC build.
 * </p>
 */
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/reports")
public interface ReportEndpoint {

    /**
     * Retrieves a list of tracking identifiers filtered by the report state.
     *
     * @param type The category of IDs to retrieve (e.g., "all", "sealed", "unsealed"). Defaults to "all".
     * @return A list of unique {@code trackingIds} matching the criteria.
     */
    @Operation(summary = "Retrieves tracking IDs of existing report of given type.")
    @APIResponse(responseCode = "200", description = "Existing tracking IDs with requested type.")
    @Path("/ids")
    @GET
    List<String> getAllIds(@QueryParam("type") @DefaultValue("all") String type);

    /**
     * Initializes a new empty tracking report for a specific build execution.
     * <p>
     * This operation is idempotent. If a report with the given ID already exists, no changes are performed.
     * </p>
     *
     * @param id The unique {@code trackingId} (typically a build content ID).
     */
    @Operation(summary = "Initialize a new tracking report for a build.")
    @APIResponse(responseCode = "201", description = "Tracking report initialized.")
    @Path("/{id}")
    @PUT
    void initReport(@PathParam("id") String id);

    /**
     * Records an artifact produced during the build execution in a specified tracking report.
     *
     * @param id The {@code trackingId} of the tracking report.
     * @param request The metadata containing artifact details (path, checksums, etc.).
     */
    @Operation(summary = "Track a build output (artifact produced by the build).")
    @APIResponse(responseCode = "204", description = "Upload tracked.")
    @APIResponse(responseCode = "409", description = "Report is sealed.")
    @Path("/{id}/uploads")
    @POST
    void trackUpload(@PathParam("id") String id, TrackUploadRequest request);

    /**
     * Records a dependency consumed during the build execution in a specified tracking report.
     *
     * @param id The {@code trackingId} of the tracking report.
     * @param request The metadata containing dependency details (origin, checksums, etc.).
     */
    @Operation(summary = "Track a build dependency (artifact consumed by the build).")
    @APIResponse(responseCode = "204", description = "Download tracked.")
    @APIResponse(responseCode = "409", description = "Report is sealed.")
    @Path("/{id}/downloads")
    @POST
    void trackDownload(@PathParam("id") String id, TrackDownloadRequest request);

    /**
     * Seals the tracking report, preventing any further modifications or additions.
     * <p>
     * After sealing, the report becomes an immutable record of the build's inputs and outputs.
     * </p>
     *
     * @param id The {@code trackingId} of the report to seal.
     */
    @Operation(summary = "Seal the tracking report for the specified key, to prevent further content logging.")
    @APIResponse(responseCode = "204", description = "Tracking report sealed.")
    @APIResponse(responseCode = "404", description = "No such tracking report.")
    @Path("/{id}/seal")
    @POST
    void sealReport(@PathParam("id") String id);

    /**
     * Fetches the complete tracking report, including all recorded uploads and downloads.
     *
     * @param id The {@code trackingId} of the tracking report.
     * @return The comprehensive {@link TrackingReport} object.
     */
    @Operation(summary = "Gets the tracking report for the specified ID.")
    @APIResponse(responseCode = "200", description = "The tracked content report.")
    @APIResponse(responseCode = "404", description = "No such tracking report.")
    @APIResponse(responseCode = "409", description = "Report is not SEALED.")
    @Path("/{id}")
    @GET
    TrackingReport getReport(@PathParam("id") String id);

    /**
     * Provides a simplified view of the report containing only the paths of uploaded artifacts.
     *
     * @param id The {@code trackingId} of the tracking report.
     * @return A list of storage paths for artifacts produced by this build.
     */
    @Operation(summary = "Get only the paths of uploaded artifacts for a given build.")
    @APIResponse(responseCode = "200", description = "List of upload paths.")
    @APIResponse(responseCode = "404", description = "No such tracking report.")
    @Path("/{id}/uploads/paths")
    @GET
    List<String> getUploadPaths(@PathParam("id") String id);

    /**
     * Permanently deletes a tracking report and all its associated metadata from the system.
     *
     * @param id The {@code trackingId} of the report to be removed.
     */
    @Operation(summary = "Delete the tracking report for the specified ID.")
    @APIResponse(responseCode = "204", description = "Tracking report deleted.")
    @Path("/{id}")
    @DELETE
    void clearReport(@PathParam("id") String id);

}
