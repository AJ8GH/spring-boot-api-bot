package com.aj.models;

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
    private Double bspLiability;
    private String status;

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
