package com.snimmo.oss.vma.valuemapping;

public record ValueMapping(Integer sourceSystemId, String sourceValue, Integer targetSystemId, String targetValue, ValueType targetValueType) {
}
