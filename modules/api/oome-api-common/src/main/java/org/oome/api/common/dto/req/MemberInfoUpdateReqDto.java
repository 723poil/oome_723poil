package org.oome.api.common.dto.req;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.oome.entity.common.enums.Gender;

import java.time.LocalDate;

@ToString
@Setter
@Getter
@AllArgsConstructor
@Builder
public class MemberInfoUpdateReqDto {

    private String phoneNumber;

    private Gender gender;

    private String githubUrl;

    private String subEmail;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private LocalDate birth;
}
