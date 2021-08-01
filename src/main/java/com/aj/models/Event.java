package com.aj.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Map;

@Entity
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String countryCode;
    private String timezone;
    private String openDate;
    private Integer marketCount;

    public Event() {
    }

    public Event(String name, String countryCode, String timezone, String openDate, Integer marketCount) {
        this.name = name;
        this.countryCode = countryCode;
        this.timezone = timezone;
        this.openDate = openDate;
        this.marketCount = marketCount;
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

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public String getOpenDate() {
        return openDate;
    }

    public void setOpenDate(String openDate) {
        this.openDate = openDate;
    }

    public Integer getMarketCount() {
        return marketCount;
    }

    public void setMarketCount(Integer marketCount) {
        this.marketCount = marketCount;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", countryCode='" + countryCode + '\'' +
                ", timezone='" + timezone + '\'' +
                ", openDate='" + openDate + '\'' +
                ", marketCount=" + marketCount +
                '}';
    }

    @JsonProperty("event")
    private void unpackNested(Map<String, Object> event) {
        setId(Long.valueOf((String) event.get("id")));
        setName((String) event.get("name"));
        setCountryCode((String) event.get("countryCode"));
        setTimezone((String) event.get("timezone"));
        setOpenDate((String) event.get("openDate"));
    }
}
