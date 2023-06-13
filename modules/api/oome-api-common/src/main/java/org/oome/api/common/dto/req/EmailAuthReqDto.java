package org.oome.api.common.dto.req;

import lombok.*;

@ToString
@Setter
@Getter
@AllArgsConstructor
@Builder
public class EmailAuthReqDto {

    private String email;

    private String authCode;
}
