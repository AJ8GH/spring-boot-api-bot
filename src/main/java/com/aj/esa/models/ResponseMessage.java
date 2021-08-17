package com.aj.esa.models;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ResponseMessage {
    private String op;
    private Long id;
    private int heartbeatMs;
    private int status;
    private String ct;
    private List<MarketChange> mc;
}
