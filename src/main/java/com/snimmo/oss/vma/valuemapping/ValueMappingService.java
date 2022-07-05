package com.snimmo.oss.vma.valuemapping;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.ApplicationScoped;
import java.util.Optional;

@ApplicationScoped
@AllArgsConstructor
@Slf4j
public class ValueMappingService {

    private final ValueMappingRepository repository;
    private final ValueMappingMapper mapper;

    public Optional<ValueMapping> find(Integer sourceSystemId, String sourceValue, Integer targetSystemId) {
        return repository.find(sourceSystemId, sourceValue, targetSystemId).map(mapper::toDomain);
    }

}
