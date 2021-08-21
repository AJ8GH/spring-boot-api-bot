package com.aj.api.bettingTypes;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CancelInstruction extends Instruction {
    private final String betId;
    private final Double sizeReduction;
}
