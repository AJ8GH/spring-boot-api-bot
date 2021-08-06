package com.aj.models;

import com.aj.repositories.MarketBookRepository;
import com.aj.repositories.MarketCatalogueRepository;
import com.aj.repositories.RunnerRepository;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class MarketBook {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String marketId;
    private String marketName;
    private String status;
    private Boolean complete;
    private Integer numberOfActiveRunners;
    private Double totalMatched;
    private Double totalAvailable;
    @OneToMany
    @ToString.Exclude
    private List<Runner> runners;

    public static void enrich(
            MarketBook marketBook,
            MarketCatalogueRepository marketCatalogueRepository) {

        if (marketCatalogueRepository.count() != 0) {
            for (MarketCatalogue market : marketCatalogueRepository.findAll()) {
                if (marketBook.getMarketId().equals(market.getMarketId())) {
                    marketBook.setMarketName(market.getMarketName());
                }
            }
        }
    }
}
