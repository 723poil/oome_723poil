package org.oome.infra.vo;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @deprecated Dto 재생산으로 제거예정
 * @author 한주성
 */
@Deprecated(since = "2023-06-02", forRemoval = true)
@Setter
@Getter
public class LoginReqVo implements Serializable {

    private static final long serialVersionUID = 7218105353552486013L;

    private String username;

    private String password;
}
