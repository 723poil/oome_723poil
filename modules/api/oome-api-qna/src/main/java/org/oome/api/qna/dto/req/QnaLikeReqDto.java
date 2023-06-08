package org.oome.api.qna.dto.req;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.oome.entity.common.enums.YN;
import org.oome.entity.member.Member;

import java.io.Serializable;

@ToString
@Setter
@Getter
@AllArgsConstructor
@Builder
@Deprecated
public class QnaLikeReqDto implements Serializable {

    private final static long serialVersionUID = 8270126307072725713L;

    @Schema(description = "좋아요 여부")
    private YN isLike;

    @Schema(description = "로그인 유저")
    private Member creater;
}
