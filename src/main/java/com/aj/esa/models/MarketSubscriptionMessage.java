package com.aj.esa.models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@Builder
public class MarketSubscriptionMessage {
    private String op;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private Map<String, List<String>> marketFilter;
    private Map<String, List<String>> marketDataFilter;
}