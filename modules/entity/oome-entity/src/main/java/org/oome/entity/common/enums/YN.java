package org.oome.entity.common.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum YN {
    Y("Y"),
    N("N");

    private final String value;
}
