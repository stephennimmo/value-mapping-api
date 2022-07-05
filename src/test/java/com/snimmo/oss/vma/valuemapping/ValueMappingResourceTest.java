package com.snimmo.oss.vma.valuemapping;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static javax.ws.rs.core.Response.Status.NOT_FOUND;

@QuarkusTest
public class ValueMappingResourceTest {

    @Test
    public void notFound() {
        given()
                .when()
                .get("/value-mapping")
                .then()
                .statusCode(NOT_FOUND.getStatusCode());
    }

}
