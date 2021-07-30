package com.aj.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Map;

@Entity
public class EventType {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private Integer marketCount;

    @JsonProperty("eventType")
    private void unpackNested(Map<String, Object> eventType) {
        this.id = Long.valueOf((String) eventType.get("id"));
        this.name = (String) eventType.get("name");
    }

    @Override
    public String toString() {
        return "EventType{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", marketCount=" + marketCount +
                '}';
    }
}
