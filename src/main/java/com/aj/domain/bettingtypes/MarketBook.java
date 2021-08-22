package com.aj.domain.bettingtypes;

import com.aj.domain.bettingenums.MarketStatus;
import com.aj.enrichment.EnrichableMarket;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class MarketBook implements EnrichableMarket {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String marketId;
    private String marketName;
    private Boolean isMarketDataDelayed;
    private MarketStatus status;
    private int betDelay;
    private Boolean bspReconciled;
    private Boolean complete;
    private Boolean inPlay;
    private int numberOfWinners;
    private int numberOfRunners;
    private Integer numberOfActiveRunners;
    private String lastMatchTime;
    private Double totalMatched;
    private Double totalAvailable;
    private Boolean crossMatching;
    private Boolean runnersVoidable;
    private long version;
    @OneToMany
    @ToString.Exclude
    private List<Runner> runners;

    private String competitionName;
    private String eventTypeName;
    private String eventName;

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
