package org.oome.api.common.dto.req;

import lombok.*;

import javax.validation.constraints.NotEmpty;

@ToString
@Setter
@Getter
@AllArgsConstructor
@Builder
public class MemberPasswordUpdateReqDto {

    @NotEmpty(message = "원본패스워드 : {invalid.NotEmpty}")
    private String originalPassword;

    @NotEmpty(message = "새패스워드 : {invalid.NotEmpty}")
    private String changedPassword;
}
