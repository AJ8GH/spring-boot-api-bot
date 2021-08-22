package com.aj.repositories;

import com.aj.domain.bettingtypes.CancelInstructionReport;
import org.springframework.data.repository.CrudRepository;

public interface InstructionReportRepository extends CrudRepository<CancelInstructionReport, Long> {
}
