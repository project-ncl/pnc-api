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

import java.io.IOException;
import java.util.List;

import javax.validation.constraints.Min;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jboss.pnc.api.bifrost.dto.Line;
import org.jboss.pnc.api.bifrost.dto.MetaData;
import org.jboss.pnc.api.bifrost.enums.Direction;
import org.jboss.pnc.api.bifrost.enums.Format;
import org.jboss.pnc.api.dto.ComponentVersion;

/**
 * @author <a href="mailto:matejonnet@gmail.com">Matej Lazar</a>
 */
@Path("/")
@Consumes(MediaType.APPLICATION_JSON)
public interface Bifrost {

    @GET
    @Path("/text")
    @Produces(MediaType.TEXT_PLAIN)
    Response getAllLines(
            @QueryParam("matchFilters") String matchFilters,
            @QueryParam("prefixFilters") String prefixFilters,
            @QueryParam("afterLine") Line afterLine,
            @QueryParam("direction") Direction direction,
            @QueryParam("format") @DefaultValue("DEFAULT") Format format,
            @QueryParam("maxLines") Integer maxLines,
            @QueryParam("batchSize") @Min(1) Integer batchSize,
            @QueryParam("batchDelay") @Min(200) Integer batchDelay,
            @QueryParam("follow") boolean follow,
            @QueryParam("timeoutProbeString") String timeoutProbeString); // if string is defined the server is sending
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                    // given string as a connection probe. The

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    List<Line> getLines(
            @QueryParam("matchFilters") String matchFilters,
            @QueryParam("prefixFilters") String prefixFilters,
            @QueryParam("afterLine") Line afterLine,
            @QueryParam("direction") @DefaultValue("ASC") Direction direction,
            @QueryParam("maxLines") Integer maxLines,
            @QueryParam("batchSize") @Min(1) Integer batchSize) throws IOException;

    @GET
    @Path("/metadata")
    @Produces(MediaType.APPLICATION_JSON)
    MetaData getMetaData(
            @QueryParam("matchFilters") String matchFilters,
            @QueryParam("prefixFilters") String prefixFilters,
            @QueryParam("afterLine") Line afterLine,
            @QueryParam("direction") @DefaultValue("ASC") Direction direction,
            @QueryParam("maxLines") Integer maxLines,
            @QueryParam("batchSize") @Min(1) Integer batchSize) throws IOException;

    @GET
    @Path("/version")
    @Produces(MediaType.APPLICATION_JSON)
    ComponentVersion getVersion();
}
