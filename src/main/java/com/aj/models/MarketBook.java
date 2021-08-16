package com.aj.models;

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
    private String competitionName;
    private String eventTypeName;
    private String eventName;
}
