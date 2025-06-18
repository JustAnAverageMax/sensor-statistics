package io.github.justanaveragemax.sensorstatistics.dto.response;

import io.github.justanaveragemax.sensorstatistics.dto.FieldViolationDto;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ValidationErrorResponse {

  @Schema(description = "Array of field violations")
  private List<@Valid FieldViolationDto> violations = new ArrayList<>();

}
