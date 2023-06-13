package org.oome.entity.member.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Data
@Setter
@Getter
public class MemberTestDto {

    private String username;

    private String nickname;

    @QueryProjection
    public MemberTestDto(String username, String nickname) {
        this.username = username;
        this.nickname = nickname;
    }
}
