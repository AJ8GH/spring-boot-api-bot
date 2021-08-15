package com.aj.esa.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class MarketCache {

    private String marketId;
    private List<RunnerCache> runnerCaches;

    // @JsonProperty("mc")
    // private void unpackRunners(List<Map<String, Object>> mc) {
    //     var rcList = (List<Map<String, List<List<Double>>>>) mc.get(0).get("rc");
    //     for (var rc : rcList) {
    //         RunnerCache runnerCache = new RunnerCache();
    //         runnerCache.unpackBatl(rc.get("batl"));
    //         newRunnerCache.unpackBatb(rc.get("batb"));
    //         newRunnerCache.unpackBdatl(rc.get("bdatl"));
    //         newRunnerCache.unpackBdatb(rc.get("bdatb"));
    //     }
    // }
}
