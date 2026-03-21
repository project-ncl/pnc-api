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

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/reports")
public interface ReportEndpoint {

    @Operation(summary = "Retrieves tracking IDs of existing report of given type.")
    @APIResponse(responseCode = "200", description = "Existing tracking IDs with requested type.")
    @Path("/ids")
    @GET
    List<String> getAllIds(@QueryParam("type") @DefaultValue("all") String type);

    @Operation(
            summary = "Initialize a new tracking report for a build.")
    @APIResponse(responseCode = "201", description = "Tracking report initialized.")
    @Path("/{id}")
    @PUT
    void initReport(@PathParam("id") String id);

    @Operation(summary = "Track a build output (artifact produced by the build).")
    @APIResponse(responseCode = "204", description = "Upload tracked.")
    @APIResponse(responseCode = "409", description = "Report is sealed.")
    @Path("/{id}/artifacts/uploads")
    @POST
    void trackUpload(@PathParam("id") String id, TrackUploadRequest request);

    @Operation(summary = "Track a build dependency (artifact consumed by the build).")
    @APIResponse(responseCode = "204", description = "Download tracked.")
    @APIResponse(responseCode = "409", description = "Report is sealed.")
    @Path("/{id}/artifacts/downloads")
    @POST
    void trackDownload(@PathParam("id") String id, TrackDownloadRequest request);

    @Operation(summary = "Seal the tracking report for the specified key, to prevent further content logging")
    @APIResponse(responseCode = "204", description = "Tracking report sealed.")
    @APIResponse(responseCode = "404", description = "No such tracking report.")
    @Path("/{id}/seal")
    @POST
    void sealReport(@PathParam("id") String id);

    @Operation(summary = "Gets the tracking report for the specified ID.")
    @APIResponse(responseCode = "200", description = "The tracked content report.")
    @APIResponse(responseCode = "404", description = "No such tracking report.")
    @Path("/{id}")
    @GET
    TrackingReport getReport(@PathParam("id") String id);

    @Operation(summary = "Get only the paths of uploaded artifacts for a given build.")
    @APIResponse(responseCode = "200", description = "List of upload paths.")
    @APIResponse(responseCode = "404", description = "No such tracking report.")
    @Path("/{id}/artifacts/uploads/paths")
    @GET
    List<String> getUploadPaths(@PathParam("id") String id);

    @Operation(summary = "Delete the tracking report for the specified ID.")
    @APIResponse(responseCode = "204", description = "Tracking report deleted.")
    @Path("/{id}")
    @DELETE
    void clearReport(@PathParam("id") String id);

}
