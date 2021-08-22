package com.aj.repositories;

import com.aj.domain.bettingtypes.EventType;
import org.springframework.data.repository.CrudRepository;

public interface EventTypeRepository extends CrudRepository<EventType, Long> {
}
