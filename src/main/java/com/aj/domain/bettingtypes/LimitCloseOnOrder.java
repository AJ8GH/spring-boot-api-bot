package com.aj.domain.bettingtypes;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class LimitCloseOnOrder {
    private final double liability;
    private final double price;
}
