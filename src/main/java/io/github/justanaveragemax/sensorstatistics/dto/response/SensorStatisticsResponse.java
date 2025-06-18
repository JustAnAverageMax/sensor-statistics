package io.github.justanaveragemax.sensorstatistics.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SensorStatisticsResponse {

  @Schema(description = "Date of fetched statistics")
  private LocalDate date;

  @Schema(description = "Total amount of sensor")
  private Integer totalSensors;

  @Schema(description = "Detailed statistics containing total amount of each sensor type at provided timestamp")
  private Map<String, Integer> sensorTypeCounts = new LinkedHashMap<>();

}
