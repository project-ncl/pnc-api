/**
 * JBoss, Home of Professional Open Source.
 * Copyright 2026-2026 Red Hat, Inc., and individual contributors
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

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.headers.Header;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.jboss.pnc.api.constants.OpenapiConstants;
import org.jboss.pnc.api.dto.ErrorResponse;

/**
 * Read-only access to the content of internal SCM repositories, for clients that cannot reach the git providers
 * themselves. Only repositories on the internal git providers configured in reqour are served. Errors are
 * {@link ErrorResponse} JSON.
 */
@Tag(name = "SCM content")
@Path("/scm")
public interface ScmContentEndpoint {

    String GZIP = "application/gzip";
    String DIFF = "text/x-diff";

    /** Response header of the archive: the revision it was made from. */
    String REVISION_HEADER = "Reqour-Revision";
    /** Response header of the diff: its old side; only set in {@value #DIFF_MODE_COMPARE} mode. */
    String DIFF_BASE_HEADER = "Reqour-Diff-Base";
    /** Response header of the diff: its new side. */
    String DIFF_HEAD_HEADER = "Reqour-Diff-Head";
    /** Response header of the diff: {@value #DIFF_MODE_COMPARE} or {@value #DIFF_MODE_COMMIT}. */
    String DIFF_MODE_HEADER = "Reqour-Diff-Mode";
    /** Value of {@value #DIFF_MODE_HEADER} when the diff is from a base that is an ancestor of the head. */
    String DIFF_MODE_COMPARE = "compare";
    /**
     * Value of {@value #DIFF_MODE_HEADER} when no base is an ancestor of a head, and the diff is of the head commit
     * against its parent.
     */
    String DIFF_MODE_COMMIT = "commit";

    String ARCHIVE_DESC = "Source archive (tar.gz) of the first of the revisions that exists in the repository";
    String DIFF_DESC = "Complete git unified diff (binary files included as git binary patches) from the first base "
            + "that is an ancestor of a head to that head; when there is none, the diff of the first existing head "
            + "commit against its parent";
    String URL_DESC = "Internal SCM repository URL";
    String REVISION_DESC = "Candidate revisions (commit, tag or branch), in order of preference";
    String BASE_DESC = "Candidate old-side revisions, in order of preference";
    String HEAD_DESC = "Candidate new-side revisions, in order of preference";

    String REVISION_HEADER_DESC = "The revision the archive was made from";
    String DIFF_BASE_HEADER_DESC = "The old side of the diff; only in " + DIFF_MODE_COMPARE + " mode";
    String DIFF_HEAD_HEADER_DESC = "The new side of the diff";
    String DIFF_MODE_HEADER_DESC = DIFF_MODE_COMPARE + " (diff from a base that is an ancestor of the head) or "
            + DIFF_MODE_COMMIT + " (diff of the head commit against its parent)";
    String NOT_SERVED_DESC = "Missing URL or candidates, or the URL is not a repository of an internal git provider "
            + "served by reqour";
    String ARCHIVE_NOT_FOUND_DESC = "The repository or all the revisions do not exist";
    String DIFF_NOT_FOUND_DESC = "The repository does not exist, or no base/head pair and no head commit was found";
    String PROVIDER_ERROR_DESC = "The git provider answered with an error";
    String PROVIDER_TIMEOUT_DESC = "The git provider did not answer in time";

    @Operation(summary = ARCHIVE_DESC)
    @APIResponses({
            @APIResponse(
                    responseCode = OpenapiConstants.SUCCESS_CODE,
                    description = OpenapiConstants.SUCCESS_DESCRIPTION,
                    content = @Content(mediaType = GZIP),
                    headers = @Header(
                            name = REVISION_HEADER,
                            description = REVISION_HEADER_DESC,
                            schema = @Schema(implementation = String.class))),
            @APIResponse(
                    responseCode = OpenapiConstants.BAD_REQUEST_CODE,
                    description = NOT_SERVED_DESC,
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON,
                            schema = @Schema(implementation = ErrorResponse.class))),
            @APIResponse(
                    responseCode = OpenapiConstants.NOT_FOUND_CODE,
                    description = ARCHIVE_NOT_FOUND_DESC,
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON,
                            schema = @Schema(implementation = ErrorResponse.class))),
            @APIResponse(
                    responseCode = OpenapiConstants.BAD_GATEWAY_CODE,
                    description = PROVIDER_ERROR_DESC,
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON,
                            schema = @Schema(implementation = ErrorResponse.class))),
            @APIResponse(
                    responseCode = OpenapiConstants.GATEWAY_TIMEOUT_CODE,
                    description = PROVIDER_TIMEOUT_DESC,
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON,
                            schema = @Schema(implementation = ErrorResponse.class))) })
    @GET
    @Path("/archive")
    @Produces(GZIP)
    Response archive(
            @Parameter(description = URL_DESC, required = true) @QueryParam("url") String url,
            @Parameter(description = REVISION_DESC, required = true) @QueryParam("revision") List<String> revisions);

    @Operation(summary = DIFF_DESC)
    @APIResponses({
            @APIResponse(
                    responseCode = OpenapiConstants.SUCCESS_CODE,
                    description = OpenapiConstants.SUCCESS_DESCRIPTION,
                    content = @Content(mediaType = DIFF),
                    headers = {
                            @Header(
                                    name = DIFF_BASE_HEADER,
                                    description = DIFF_BASE_HEADER_DESC,
                                    schema = @Schema(implementation = String.class)),
                            @Header(
                                    name = DIFF_HEAD_HEADER,
                                    description = DIFF_HEAD_HEADER_DESC,
                                    schema = @Schema(implementation = String.class)),
                            @Header(
                                    name = DIFF_MODE_HEADER,
                                    description = DIFF_MODE_HEADER_DESC,
                                    schema = @Schema(
                                            implementation = String.class,
                                            enumeration = { DIFF_MODE_COMPARE, DIFF_MODE_COMMIT })) }),
            @APIResponse(
                    responseCode = OpenapiConstants.BAD_REQUEST_CODE,
                    description = NOT_SERVED_DESC,
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON,
                            schema = @Schema(implementation = ErrorResponse.class))),
            @APIResponse(
                    responseCode = OpenapiConstants.NOT_FOUND_CODE,
                    description = DIFF_NOT_FOUND_DESC,
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON,
                            schema = @Schema(implementation = ErrorResponse.class))),
            @APIResponse(
                    responseCode = OpenapiConstants.BAD_GATEWAY_CODE,
                    description = PROVIDER_ERROR_DESC,
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON,
                            schema = @Schema(implementation = ErrorResponse.class))),
            @APIResponse(
                    responseCode = OpenapiConstants.GATEWAY_TIMEOUT_CODE,
                    description = PROVIDER_TIMEOUT_DESC,
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON,
                            schema = @Schema(implementation = ErrorResponse.class))) })
    @GET
    @Path("/diff")
    @Produces(DIFF)
    Response diff(
            @Parameter(description = URL_DESC, required = true) @QueryParam("url") String url,
            @Parameter(description = BASE_DESC, required = true) @QueryParam("base") List<String> bases,
            @Parameter(description = HEAD_DESC, required = true) @QueryParam("head") List<String> heads);
}
