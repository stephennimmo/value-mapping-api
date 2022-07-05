package com.snimmo.oss.vma.valuemapping;

import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static io.restassured.RestAssured.given;
import static javax.ws.rs.core.Response.Status.OK;
import static org.assertj.core.api.Assertions.assertThat;

@QuarkusTest
public class ValueMappingResourceTest {

    @InjectMock
    ValueMappingRepository valueMappingRepository;

    @Test
    public void getBySourceSystemIdAndSourceValueAndTargetSystemId() {
        Mockito.when(valueMappingRepository.find(1, "TEST", 2))
                .thenReturn(Optional.of(new ValueMappingEntity(1, 1, "TEST", 2, "TEST2", ValueType.STRING)));
        ValueMapping valueMapping = given()
                .when()
                .get("/value-mapping/1/TEST/2")
                .then()
                .statusCode(OK.getStatusCode())
                .extract()
                .as(ValueMapping.class);
        assertThat(valueMapping)
                .isNotNull()
                .isEqualTo(new ValueMapping(1, 1, "TEST", 2, "TEST2", ValueType.STRING));
    }

}
