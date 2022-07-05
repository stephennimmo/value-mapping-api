package com.snimmo.oss.vma.valuemapping;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity(name = "ValueMapping")
@Table(name = "value_mapping")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ValueMappingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "value_mapping_id")
    private Integer valueMappingId;

    @Column(name = "source_system_id")
    @NotNull
    private Integer sourceSystemId;

    @Column(name = "source_value")
    @NotEmpty
    private String sourceValue;

    @Column(name = "target_system_id")
    @NotNull
    private Integer targetSystemId;

    @Column(name = "target_value")
    @NotEmpty
    private String targetValue;

    @Enumerated(EnumType.STRING)
    @Column(name = "target_value_type")
    @NotNull
    private ValueType targetValueType;

}
