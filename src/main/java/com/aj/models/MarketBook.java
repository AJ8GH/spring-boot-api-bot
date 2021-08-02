package com.aj.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
public @Getter
@Setter
@ToString
@AllArgsConstructor
class MarketBook {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String marketId;
    private String status;
    private Boolean complete;
    private Integer numberOfActiveRunners;
    private Double totalMatched;
    private Double totalAvailable;
    @OneToMany
    @ToString.Exclude
    private List<Runner> runners;

}
