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

package org.jboss.pnc.api.bifrost.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/final-log")
public interface FinalLogRest {

    @Path("/{processContext}/delete")
    @DELETE
    Response deleteFinalLog(@PathParam("processContext") String processContext);

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes
    @Path("/{buildId}/{tag}")
    Response getFinalLog(@PathParam("buildId") String buildId, @PathParam("tag") String tag);

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes
    @Path("/{buildId}/{tag}/size")
    public long getFinalLogSize(@PathParam("buildId") String buildId, @PathParam("tag") String tag);
}
