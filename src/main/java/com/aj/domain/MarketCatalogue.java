package com.aj.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Map;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class MarketCatalogue {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String marketId;
    private String marketName;
    private Double totalMatched;
    @OneToMany(cascade = {CascadeType.ALL})
    @ToString.Exclude
    private List<Runner> runners;
    private String eventTypeName;
    private String eventName;
    private String competitionName;

    @JsonProperty("event")
    private void unpackEvent(Map<String, Object> event) {
        this.eventName = (String) event.get("name");
    }

    @JsonProperty("eventType")
    private void unpackEventType(Map<String, Object> eventType) {
        this.eventTypeName = (String) eventType.get("name");
    }

    @JsonProperty("competition")
    private void unpackCompetition(Map<String, Object> competition) {
        this.competitionName = (String) competition.get("name");
    }

}
