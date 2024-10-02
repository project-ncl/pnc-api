/**
 * JBoss, Home of Professional Open Source.
 * Copyright 2021 Red Hat, Inc., and individual contributors
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
package org.jboss.pnc.api.causeway.rest;

import org.jboss.pnc.api.causeway.dto.push.BuildPushRequest;
import org.jboss.pnc.api.causeway.dto.untag.UntagRequest;
import org.jboss.pnc.api.dto.ComponentVersion;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

@Consumes(MediaType.APPLICATION_JSON)
@Path("/")
public interface Causeway {
    @POST
    @Path("/push-build")
    void importBuild(@Valid BuildPushRequest buildPushRequest);

    @POST
    @Path("/untag")
    void untagBuild(@Valid UntagRequest request);

    @GET
    @Path("/version")
    ComponentVersion getVersion();
}
