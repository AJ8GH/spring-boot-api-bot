package com.aj.repositories;

import com.aj.models.Bet;
import org.springframework.data.repository.CrudRepository;

public interface BetRepository extends CrudRepository<Bet, Long> {
}
