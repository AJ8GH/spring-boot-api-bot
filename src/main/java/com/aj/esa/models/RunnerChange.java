package com.aj.esa.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RunnerChange {
    @JsonProperty("id")
    private Long selectionId;
    private List<List<Double>> batl;
    private List<List<Double>> batb;
    private List<List<Double>> bdatb;
    private List<List<Double>> bdatl;
}
