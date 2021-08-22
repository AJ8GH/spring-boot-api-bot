package com.aj.models;

import com.aj.models.bettingTypes.Instruction;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CancelInstruction extends Instruction {
    private final String betId;
    private final Double sizeReduction;
}
