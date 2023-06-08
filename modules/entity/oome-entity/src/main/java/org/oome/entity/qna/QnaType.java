package org.oome.entity.qna;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum QnaType {

    Q("Q"),
    A("A");

    private final String type;
}
