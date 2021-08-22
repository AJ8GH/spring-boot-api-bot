package com.aj.domain;

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
@Builder
public class EventType {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private Integer marketCount;

    @JsonProperty("eventType")
    private void unpackNested(Map<String, Object> eventType) {
        setId(Long.valueOf((String) eventType.get("id")));
        setName((String) eventType.get("name"));
    }
}
