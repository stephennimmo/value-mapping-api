package com.snimmo.oss.vma;

import com.snimmo.oss.vma.system.System;
import com.snimmo.oss.vma.system.SystemEntity;
import com.snimmo.oss.vma.system.SystemRepository;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static io.restassured.RestAssured.given;
import static javax.ws.rs.core.Response.Status.OK;
import static org.assertj.core.api.Assertions.assertThat;

@QuarkusTest
public class SystemResourceTest {

    @InjectMock
    SystemRepository systemRepository;

    @BeforeEach
    public void beforeEach() {
        Mockito.when(systemRepository.listAll()).thenReturn(List.of(new SystemEntity(1, "TEST")));
    }

    @Test
    public void getAll() {
        System[] systems = given()
                .when()
                .get("/systems")
                .then()
                .statusCode(OK.getStatusCode())
                .extract()
                .as(System[].class);
        assertThat(systems)
                .hasSize(1)
                .contains(new System(1, "TEST"));
    }

}