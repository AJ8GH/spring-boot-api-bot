package com.aj.api.bettingTypes;

import com.aj.api.enumTypes.PersistenceType;
import com.aj.api.enumTypes.TimeInForce;
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
