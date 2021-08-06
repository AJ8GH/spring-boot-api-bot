package com.aj.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import java.util.Map;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Bet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long betId;
    private String marketId;
    private Long selectionId;
    private String placedDate;
    private Double price;
    private Double size;
    private String side;
    private Double bspLiability;
    private String status;

    @JsonProperty("priceSize")
    private void unpackNested(Map<String, Object> priceSize) {
        setPrice((Double) priceSize.get("price"));
        setSize((Double) priceSize.get("size"));
    }
}
