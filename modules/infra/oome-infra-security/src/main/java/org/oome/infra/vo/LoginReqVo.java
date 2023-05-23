package org.oome.infra.vo;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class LoginReqVo implements Serializable {

    private static final long serialVersionUID = 7218105353552486013L;

    private String username;

    private String password;
}
