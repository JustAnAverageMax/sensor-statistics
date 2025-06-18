package io.github.justanaveragemax.sensorstatistics.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Model that contains a single field validation violation info")
public class FieldViolationDto {

  @Schema(description = "Name of the field that was violated")
  @NotNull(message = "'fieldName' should not be null")
  @NotBlank(message = "'fieldName' should not be empty")
  private String fieldName;

  @Schema(description = "Message containing details of the violation")
  @NotNull(message = "'violationMessage' should not be null")
  @NotBlank(message = "'violationMessage' should not be empty")
  private String violationMessage;
}
