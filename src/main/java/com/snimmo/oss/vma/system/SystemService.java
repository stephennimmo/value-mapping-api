package com.snimmo.oss.vma.system;

import com.snimmo.oss.vma.ServiceException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@ApplicationScoped
@Slf4j
@AllArgsConstructor
public class SystemService {

    private final SystemRepository repository;
    private final SystemMapper mapper;

    public List<System> findAll() {
        return repository.listAll().stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    public Optional<System> findById(Integer systemId) {
        return repository.findByIdOptional(systemId).map(mapper::toDomain);
    }

    @Transactional
    public System create(System system) {
        if (!Objects.isNull(system.systemId())) {
            throw new ServiceException("Create System error. System.systemId must be null");
        }
        SystemEntity entity = mapper.toEntity(system);
        repository.persist(entity);
        return mapper.toDomain(entity);
    }

}
