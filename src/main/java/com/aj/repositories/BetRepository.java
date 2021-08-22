package com.aj.repositories;

import com.aj.domain.Bet;
import org.springframework.data.repository.CrudRepository;

public interface BetRepository extends CrudRepository<Bet, Long> {
}
