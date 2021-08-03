package com.aj.repositories;

import com.aj.models.ExchangePrice;
import org.springframework.data.repository.CrudRepository;

public interface ExchangePriceRepository extends CrudRepository<ExchangePrice, Long> {
}
