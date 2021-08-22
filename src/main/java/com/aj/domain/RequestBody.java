package com.aj.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;

import java.util.Set;

@Builder
@Getter
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class RequestBody {
    private final String marketId;
    private final Set<String> marketIds;
    private final Set<Instruction> instructions;
    private final Set<String> betIds;
    private final OrderBy orderBy;
    private final SortDir sortDir;
    private final int fromRecord;
    private final int recordCount;
    private final boolean includeItemDescription;
    private final OrderProjection orderProjection;
    private final PriceProjection priceProjection;
    private final MarketFilter filter;
    private final Set<MarketProjection> marketProjection;
    private final MarketSort sort;
    private final int maxResults;
    private final String locale;
}
