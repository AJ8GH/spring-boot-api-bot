package com.aj.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class PlaceInstruction extends Instruction {
    private final long selectionId;
    private final double handicap;
    private final Side side;
    private final OrderType orderType;
    private final LimitOrder limitOrder;
    private final LimitCloseOnOrder limitCloseOnOrder;
    private final MarketCloseOnOrder marketCloseOnOrder;
    private final String customerOrderRef;
}
