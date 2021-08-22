package com.aj.domain.esa;

import com.aj.enrichment.EnrichableMarket;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MarketChange implements EnrichableMarket {

    @JsonProperty("id")
    private String marketId;
    private String marketName;
    private String eventTypeName;
    private String eventName;
    private String competitionName;
    private List<RunnerChange> rc;

    @Override
    public String getMarketId() {
        return marketId;
    }

    @Override
    public void setMarketName(String marketName) {
        this.marketName = marketName;
    }

    @Override
    public void setEventTypeName(String eventTypeName) {
        this.eventTypeName = eventTypeName;
    }

    @Override
    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    @Override
    public void setCompetitionName(String competitionName) {
        this.competitionName = competitionName;
    }
}
