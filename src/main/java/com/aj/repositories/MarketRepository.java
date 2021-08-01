package com.aj.repositories;

import com.aj.models.Market;
import org.springframework.data.repository.CrudRepository;

public interface MarketRepository extends CrudRepository<Market, Long> {
}
