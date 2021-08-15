package com.aj.esa.models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class AuthenticationMessage {
    private String op;
    private String appKey;
    private String session;
}