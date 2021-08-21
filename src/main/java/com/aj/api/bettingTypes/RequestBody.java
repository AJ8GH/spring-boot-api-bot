package com.aj.api.bettingTypes;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RequestBody {
    private final MarketFilter filter;
}
