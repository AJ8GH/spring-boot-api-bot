package com.aj.models;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
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

}
