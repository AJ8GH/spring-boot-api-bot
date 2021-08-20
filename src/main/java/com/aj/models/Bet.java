package com.aj.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
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
public class Bet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String betId;
    private String marketId;
    private Long selectionId;
    private String marketName;
    private String runnerName;
    private String eventName;
    private String placedDate;
    private Double price;
    private Double size;
    private String side;
    private Double sizeMatched;
    private Double sizeRemaining;
    private Double handicap;
    private Double bspLiability;
    private String status;

    public static Bet findByBetId(String betId, Iterable<Bet> bets) {
        for (Bet bet : bets) {
            if (bet.getBetId().equals(betId)) return bet;
        }
        return null;
    }

    public LocalDateTime placedDateTime() {
        if (placedDate != null) {
            return LocalDateTime.parse(
                    placedDate.substring(0, placedDate.length() - 1));
        }
        return null;
    }

    @JsonProperty("priceSize")
    private void unpackNested(Map<String, Object> priceSize) {
        this.price = (Double) priceSize.get("price");
        this.size = (Double) priceSize.get("size");
    }

    @JsonProperty("instructionReports")
    private void unpackPlaceExecutionReport(List<Map<String, Object>> report) {
        this.betId = (String) report.get(0).get("betId");
    }
}
