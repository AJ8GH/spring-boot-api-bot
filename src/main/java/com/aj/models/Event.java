package com.aj.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Event extends DateTimeParser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String countryCode;
    private String timezone;
    private String openDate;
    private Integer marketCount;

    @JsonProperty("event")
    private void unpackNested(Map<String, Object> event) {
        this.id = Long.valueOf((String) event.get("id"));
        this.name = (String) event.get("name");
        this.countryCode = (String) event.get("countryCode");
        this.timezone = (String) event.get("timezone");
        this.openDate = (String) event.get("openDate");
    }
}
