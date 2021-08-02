package com.aj.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Map;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Event {

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
        setId(Long.valueOf((String) event.get("id")));
        setName((String) event.get("name"));
        setCountryCode((String) event.get("countryCode"));
        setTimezone((String) event.get("timezone"));
        setOpenDate((String) event.get("openDate"));
    }
}
