package com.snimmo.oss.vma.system;

import javax.validation.constraints.NotEmpty;

public record System(Integer systemId, @NotEmpty String name) {
}
