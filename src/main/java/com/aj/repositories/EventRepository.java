package com.aj.repositories;

import com.aj.domain.bettingtypes.Event;
import org.springframework.data.repository.CrudRepository;

public interface EventRepository extends CrudRepository<Event, Long> {
}
