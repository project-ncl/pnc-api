/**
 * JBoss, Home of Professional Open Source.
 * Copyright 2024-2024 Red Hat, Inc., and individual contributors
 * as indicated by the @author tags.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
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
import org.jboss.pnc.api.reqour.dto.InternalSCMCreationRequest;

/**
 * Endpoint for creation of internal SCM repository.
 */
@Tag(name = "Internal SCM")
@Consumes(MediaType.APPLICATION_JSON)
@Path("/internal-scm")
public interface InternalSCMRepositoryCreationEndpoint {

    String CREATION_DESC = "Create new internal SCM repository";

    @Operation(summary = CREATION_DESC)
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
    void createInternalSCMRepository(@Valid InternalSCMCreationRequest creationRequest);
}
