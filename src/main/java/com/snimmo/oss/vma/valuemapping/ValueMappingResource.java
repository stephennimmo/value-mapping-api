package com.snimmo.oss.vma.valuemapping;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/value-mappings")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "value-mapping", description = "Value Mapping Operations")
@AllArgsConstructor
@Slf4j
public class ValueMappingResource {

    private final ValueMappingService valueMappingService;

    @GET
    @Path("/{sourceSystemId}/{targetSystemId}")
    @APIResponse(
            responseCode = "200",
            description = "Get ValueMappings by sourceSystemId and targetSystemId",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(type = SchemaType.ARRAY, implementation = ValueMapping.class)
            )
    )
    public Response getById(
            @Parameter(name = "sourceSystemId", required = true) @PathParam("sourceSystemId") Integer sourceSystemId,
            @Parameter(name = "targetSystemId", required = true) @PathParam("targetSystemId") Integer targetSystemId
    ) {
        return Response.ok(valueMappingService.find(sourceSystemId, targetSystemId)).build();
    }

    @GET
    @Path("/{sourceSystemId}/{sourceValue}/{targetSystemId}")
    @APIResponse(
            responseCode = "200",
            description = "Get ValueMapping by sourceSystemId, sourceValue and targetSystemId",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(type = SchemaType.OBJECT, implementation = ValueMapping.class)
            )
    )
    @APIResponse(
            responseCode = "404",
            description = "ValueMapping does not exist for sourceSystemId, sourceValue and targetSystemId",
            content = @Content(mediaType = MediaType.APPLICATION_JSON)
    )
    public Response getById(
            @Parameter(name = "sourceSystemId", required = true) @PathParam("sourceSystemId") Integer sourceSystemId,
            @Parameter(name = "sourceValue", required = true) @PathParam("sourceValue") String sourceValue,
            @Parameter(name = "targetSystemId", required = true) @PathParam("targetSystemId") Integer targetSystemId
    ) {
        return valueMappingService.find(sourceSystemId, sourceValue, targetSystemId)
                .map(valueMapping -> Response.ok(valueMapping).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

}