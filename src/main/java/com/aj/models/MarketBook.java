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
            MarketCatalogueRepository repository) {
        for (MarketCatalogue marketCatalogue : repository.findAll()) {
            if (marketBook.getMarketId().equals(marketCatalogue.getMarketId())) {
                marketBook.setMarketName(marketCatalogue.getMarketName());
                enrichRunners(marketBook, marketCatalogue);
            }
        }
    }

    private static void enrichRunners(
            MarketBook marketBook, MarketCatalogue marketCatalogue) {

        if (marketBook.getRunners() != null) {
            for (Runner bookRunner : marketBook.getRunners()) {
                for (Runner catalogueRunner : marketCatalogue.getRunners()) {
                    if (bookRunner.getSelectionId().equals(catalogueRunner.getSelectionId())) {
                        bookRunner.setRunnerName(catalogueRunner.getRunnerName());
                    }
                }
            }
        }
    }
}
