package com.snimmo.oss.vma.system;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
@AllArgsConstructor
@Slf4j
public class SystemService {

    private final SystemRepository repository;
    private final SystemMapper mapper;

    public List<System> findAll() {
        return this.mapper.toDomainList(repository.listAll());
    }

}
