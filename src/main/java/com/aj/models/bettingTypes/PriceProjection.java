package com.aj.models.bettingTypes;

import com.aj.models.enumTypes.PriceData;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;

import java.util.Set;

@Builder
@Getter
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class PriceProjection {
    private final Set<PriceData> priceData;
    private final boolean virtualise;
}
