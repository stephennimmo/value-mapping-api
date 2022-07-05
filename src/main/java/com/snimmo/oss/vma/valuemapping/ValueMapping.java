package com.snimmo.oss.vma.valuemapping;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public record ValueMapping(
        Integer valueMappingId,
        @NotNull Integer sourceSystemId,
        @NotEmpty String sourceValue,
        @NotNull Integer targetSystemId,
        @NotEmpty String targetValue,
        @NotNull ValueType targetValueType) {
}
