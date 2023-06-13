package org.oome.infra.redis.hash.server1;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;
import org.springframework.data.redis.core.index.Indexed;

@Getter
@AllArgsConstructor
@Builder
@RedisHash("authcode")
public class AuthCode {

    @Id
    private String id;

    @Indexed
    private String authcode;

    @TimeToLive
    private Long expiration;
}
