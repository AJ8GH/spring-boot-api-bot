package com.aj.domain.bettingtypes;

import com.aj.domain.bettingenums.RunnerStatus;
import com.aj.enrichment.EnrichableRunner;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Map;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Runner implements EnrichableRunner {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private long selectionId;
    private double handicap;
    private RunnerStatus status;
    private double adjustmentFactor;
    private double lastPriceTraded;
    private double totalMatched;
    private String removalDate;

    private String runnerName;
    @OneToMany(cascade = {CascadeType.ALL})
    @ToString.Exclude
    private List<ExchangePrice> availableToBack;
    @OneToMany(cascade = {CascadeType.ALL})
    @ToString.Exclude
    private List<ExchangePrice> availableToLay;
    @OneToMany(cascade = {CascadeType.ALL})
    @ToString.Exclude
    private List<ExchangePrice> tradedVolume;

    @JsonProperty("ex")
    private void unpackNested(Map<String, List<ExchangePrice>> ex) {
        setAvailableToBack(ex.get("availableToBack"));
        setAvailableToLay(ex.get("availableToLay"));
        setTradedVolume(ex.get("tradedVolume"));
    }

    @Override
    public long getSelectionId() {
        return selectionId;
    }

    @Override
    public void setRunnerName(String runnerName) {
        this.runnerName = runnerName;
    }
}
