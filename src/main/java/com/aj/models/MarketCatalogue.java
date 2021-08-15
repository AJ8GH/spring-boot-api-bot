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
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "eventType_ID")
    private EventType eventType;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "event_ID")
    private Event event;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "competition_ID")
    private Competition competition;
}
