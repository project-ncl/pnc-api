/**
 * Copyright 2024 Red Hat, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package org.jboss.pnc.api.reqour.rest;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.jboss.pnc.api.constants.OpenapiConstants;
import org.jboss.pnc.api.dto.ErrorResponse;
import org.jboss.pnc.api.reqour.dto.AdjustRequest;

/**
 * Endpoint for triggering the alignment operation.
 */
@Tag(name = "Adjust")
@Consumes(MediaType.APPLICATION_JSON)
@Path("/adjust")
public interface AdjustEndpoint {

    String ADJUST_DESC = "Trigger the alignment operation";

    @Operation(summary = ADJUST_DESC)
    @APIResponses({
            @APIResponse(
                    responseCode = OpenapiConstants.ACCEPTED_CODE,
                    description = OpenapiConstants.ACCEPTED_DESCRIPTION),
            @APIResponse(
                    responseCode = OpenapiConstants.BAD_REQUEST_CODE,
                    description = OpenapiConstants.BAD_REQUEST_DESCRIPTION,
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @APIResponse(
                    responseCode = OpenapiConstants.SERVER_ERROR_CODE,
                    description = OpenapiConstants.SERVER_ERROR_DESCRIPTION,
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))) })
    @POST
    void adjust(@Valid AdjustRequest adjustRequest);
}
