package com.aj.domain.esa;

import com.aj.enrichment.EnrichableRunner;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.Collections;
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
    private List<List<Double>> batl = Collections.emptyList();
    private List<List<Double>> batb = Collections.emptyList();
    private List<List<Double>> bdatb = Collections.emptyList();
    private List<List<Double>> bdatl = Collections.emptyList();

    @Override
    public long getSelectionId() {
        return selectionId;
    }

    @Override
    public void setRunnerName(String runnerName) {
        this.runnerName = runnerName;
    }
}
