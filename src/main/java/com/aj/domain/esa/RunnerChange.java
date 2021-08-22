package com.aj.domain.esa;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RunnerChange {
    @JsonProperty("id")
    private Long selectionId;
    private String runnerName;
    private List<List<Double>> batl;
    private List<List<Double>> batb;
    private List<List<Double>> bdatb;
    private List<List<Double>> bdatl;
}