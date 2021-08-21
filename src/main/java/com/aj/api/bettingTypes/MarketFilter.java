package com.aj.api.bettingTypes;

import lombok.Builder;

import java.util.Set;

@Builder
public class MarketFilter {
    private final Set<String> eventTypeIds;
    private final Set<String> eventIds;
    private final Set<String> competitionIds;
    private final Set<String> marketIds;
}
