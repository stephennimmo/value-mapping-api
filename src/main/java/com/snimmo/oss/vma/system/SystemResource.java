package com.snimmo.oss.vma.system;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;

@Path("/systems")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "system", description = "System Operations")
@AllArgsConstructor
@Slf4j
public class SystemResource {

    private final SystemService service;

    @GET
    @APIResponse(
            responseCode = "200",
            description = "Get All Systems",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(type = SchemaType.ARRAY, implementation = System.class)
            )
    )
    public Response get() {
        return Response.ok(service.findAll()).build();
    }

    @GET
    @Path("/{systemId}")
    @APIResponse(
            responseCode = "200",
            description = "Get System by systemId",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(type = SchemaType.OBJECT, implementation = System.class)
            )
    )
    @APIResponse(
            responseCode = "404",
            description = "System does not exist for systemId",
            content = @Content(mediaType = MediaType.APPLICATION_JSON)
    )
    public Response getById(@Parameter(name = "systemId", required = true) @PathParam("systemId") Integer systemId) {
        return service.findById(systemId)
                .map(system -> Response.ok(system).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    @POST
    @APIResponse(
            responseCode = "201",
            description = "System Created",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(type = SchemaType.OBJECT, implementation = System.class)
            )
    )
    @APIResponse(
            responseCode = "400",
            description = "Invalid System",
            content = @Content(mediaType = MediaType.APPLICATION_JSON)
    )
    @APIResponse(
            responseCode = "400",
            description = "System already exists for systemId",
            content = @Content(mediaType = MediaType.APPLICATION_JSON)
    )
    public Response post(@NotNull @Valid System system, @Context UriInfo uriInfo) {
        system = service.create(system);
        URI uri = uriInfo.getAbsolutePathBuilder().path(Integer.toString(system.systemId())).build();
        return Response.created(uri).entity(system).build();
    }

}