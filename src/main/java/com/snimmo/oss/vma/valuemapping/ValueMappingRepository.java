package com.snimmo.oss.vma.valuemapping;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import io.quarkus.panache.common.Parameters;

import javax.enterprise.context.ApplicationScoped;
import java.util.Optional;

@ApplicationScoped
public class ValueMappingRepository implements PanacheRepositoryBase<ValueMappingEntity, Integer> {

    public Optional<ValueMappingEntity> find(Integer sourceSystemId, String sourceValue, Integer targetSystemId) {
        return this.find("sourceSystemId = :sourceSystemId and sourceValue = :sourceValue and targetSystemId = :targetSystemId",
                Parameters.with("sourceSystemId", sourceSystemId).and("sourceValue", sourceValue).and("targetSystemId", targetSystemId))
                .firstResultOptional();
    }

}
