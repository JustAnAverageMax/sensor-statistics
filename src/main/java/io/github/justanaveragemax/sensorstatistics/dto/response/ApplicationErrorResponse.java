package io.github.justanaveragemax.sensorstatistics.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationErrorResponse {

  @Schema(description = "Error code", example = "400")
  private Integer code;

  @Schema(description = "Error description", example = "Server exploded")
  private String description;

  @Schema(description = "Error timestamp", example = "2025-10-25T14:30:45Z")
  private String timestamp;

}
