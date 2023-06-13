package org.oome.infra.redis.hash.server1;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthCodeRedisRepository extends CrudRepository<AuthCode, String> {
}
