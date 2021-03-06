package com.aj.domain.esa;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class AuthenticationMessage extends EsaMessage {
    private String op;
    private String appKey;
    private String session;
}
