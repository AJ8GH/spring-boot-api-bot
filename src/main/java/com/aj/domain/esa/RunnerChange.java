package com.aj.domain.esa;

import com.aj.enrichment.EnrichableRunner;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RunnerChange implements EnrichableRunner {
    @JsonProperty("id")
    private long selectionId;
    private String runnerName;
    private List<List<Double>> batl;
    private List<List<Double>> batb;
    private List<List<Double>> bdatb;
    private List<List<Double>> bdatl;

    @Override
    public long getSelectionId() {
        return selectionId;
    }

    @Override
    public void setRunnerName(String runnerName) {
        this.runnerName = runnerName;
    }
}
