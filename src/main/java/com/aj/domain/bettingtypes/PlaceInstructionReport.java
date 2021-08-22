package com.aj.domain.bettingtypes;

import com.aj.domain.bettingenums.InstructionReportErrorCode;
import com.aj.domain.bettingenums.InstructionReportStatus;
import com.aj.domain.bettingenums.OrderStatus;
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
