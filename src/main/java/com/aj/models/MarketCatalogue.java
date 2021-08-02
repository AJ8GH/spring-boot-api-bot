package com.aj.models;

import javax.persistence.*;
import java.util.List;

@Entity
public class MarketCatalogue {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique = true)
    private String marketId;
    private String marketName;
    private Double totalMatched;

    @OneToMany(cascade = {CascadeType.ALL})
    private List<Runner> runners;

    public MarketCatalogue() {
    }

    public MarketCatalogue(String marketName, Double totalMatched, List<Runner> runners) {
        this.marketName = marketName;
        this.totalMatched = totalMatched;
        this.runners = runners;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMarketId() {
        return marketId;
    }

    public void setMarketId(String marketId) {
        this.marketId = marketId;
    }

    public String getMarketName() {
        return marketName;
    }

    public void setMarketName(String marketName) {
        this.marketName = marketName;
    }

    public Double getTotalMatched() {
        return totalMatched;
    }

    public void setTotalMatched(Double totalMatched) {
        this.totalMatched = totalMatched;
    }

    public List<Runner> getRunners() {
        return runners;
    }

    public void setRunners(List<Runner> runners) {
        this.runners = runners;
    }

    @Override
    public String toString() {
        return "MarketCatalogue{" +
                "marketId=" + marketId +
                ", marketName='" + marketName + '\'' +
                ", totalMatched=" + totalMatched +
                ", runners=" + runners +
                '}';
    }
}
