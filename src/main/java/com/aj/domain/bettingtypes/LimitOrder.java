package com.aj.domain.bettingtypes;

import com.aj.domain.bettingenums.PersistenceType;
import com.aj.domain.bettingenums.TimeInForce;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class LimitOrder {
    private final double size;
    private final double price;
    private final PersistenceType persistenceType;
    private final TimeInForce timeInForce;
}
