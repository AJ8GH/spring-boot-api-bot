package com.aj.domain.esa;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MarketChange {

    @JsonProperty("id")
    private String marketId;
    private String marketName;
    private String eventTypeName;
    private String eventName;
    private String competitionName;
    private List<RunnerChange> rc;
}