package com.aj.models;

import com.aj.models.enumTypes.InstructionReportErrorCode;
import com.aj.models.enumTypes.InstructionReportStatus;
import com.aj.models.enumTypes.OrderStatus;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class PlaceInstructionReport extends DateTimeParser {
    private final InstructionReportStatus status;
    private final InstructionReportErrorCode errorCode;
    private final OrderStatus orderStatus;
    private final PlaceInstruction instruction;
    private final String betId;
    private final String placedDate;
    private final double averagePriceMatched;
    private final double sizeMatched;
}
