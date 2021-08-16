package com.aj.esa.models;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ResponseMessage {
    private String op;
    private Long id;
    private int heartbeatms;
    private int status;
    private List<MarketChange> mc;
}
