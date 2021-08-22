package com.aj.repositories;

import com.aj.domain.bettingtypes.UserSession;
import org.springframework.data.repository.CrudRepository;

public interface UserSessionRepository extends CrudRepository<UserSession, Long> {
}
