package io.github.justanaveragemax.sensorstatistics.validation.validator;

import io.github.justanaveragemax.sensorstatistics.exception.BadRequestException;
import java.time.LocalDate;
import org.springframework.stereotype.Component;

@Component
public class DateRangeValidator {

  public void validateDateRange(LocalDate start, LocalDate end) {
    boolean bothSet = start != null && end != null;
    boolean bothNull = start == null && end == null;

    if (!(bothNull || bothSet)) {
      throw new BadRequestException("Either both dates must be set or neither");
    }

    if (bothSet && start.isAfter(end)) {
      throw new BadRequestException("Start date should be before or equal to end date");
    }

  }
}
