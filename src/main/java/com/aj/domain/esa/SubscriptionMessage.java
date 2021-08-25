package com.aj.domain.esa;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;
import java.util.Map;

@Getter
@Builder
public class SubscriptionMessage extends EsaMessage {
    private String op;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private Map<String, List<String>> marketFilter;
    private Map<String, List<String>> marketDataFilter;
    private int heartbeatMs;
}
