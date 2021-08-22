package com.aj.models.bettingTypes;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;

import java.util.Set;

@Builder
@Getter
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class MarketFilter {
    private final Set<String> eventTypeIds;
    private final Set<String> eventIds;
    private final Set<String> competitionIds;
    private final Set<String> marketIds;
}
