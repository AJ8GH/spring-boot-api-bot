package com.aj.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;
import java.util.Map;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CancelExecutionReport {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String status;
    private String marketId;
    private Double sizeCancelled;
    private String cancelledDate;

    @JsonProperty("instructionReports")
    private void unpackNested(List<Map<String, Object>> report) {
        this.sizeCancelled = (Double) report.get(0).get("sizeCancelled");
        this.cancelledDate = (String) report.get(0).get("cancelledDate");
    }
}
