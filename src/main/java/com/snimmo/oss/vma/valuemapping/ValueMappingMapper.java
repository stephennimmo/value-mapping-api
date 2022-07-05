package com.snimmo.oss.vma.valuemapping;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "cdi")
public interface ValueMappingMapper {

    List<ValueMapping> toDomainList(List<ValueMappingEntity> entities);

    ValueMapping toDomain(ValueMappingEntity entity);

    @InheritInverseConfiguration(name = "toDomain")
    ValueMappingEntity toEntity(ValueMapping domain);

    void updateEntityFromDomain(ValueMapping domain, @MappingTarget ValueMappingEntity entity);

    void updateDomainFromEntity(ValueMappingEntity entity, @MappingTarget ValueMapping domain);

}