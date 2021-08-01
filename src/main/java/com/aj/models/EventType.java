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

    public EventType() {
    }

    public EventType(String name, Integer marketCount) {
        this.name = name;
        this.marketCount = marketCount;
    }

    @JsonProperty("eventType")
    private void unpackNested(Map<String, Object> eventType) {
        setId(Long.valueOf((String) eventType.get("id")));
        setName((String) eventType.get("name"));
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getMarketCount() {
        return marketCount;
    }

    public void setMarketCount(Integer marketCount) {
        this.marketCount = marketCount;
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
