package org.oome.entity.common.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Gender {
    F("F"),
    M("M");

    private final String value;
}
