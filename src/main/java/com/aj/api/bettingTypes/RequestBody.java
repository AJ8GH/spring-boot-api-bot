package com.aj.api.bettingTypes;

import lombok.Builder;

@Builder
public class RequestBody {
    private final MarketFilter filter;
}
