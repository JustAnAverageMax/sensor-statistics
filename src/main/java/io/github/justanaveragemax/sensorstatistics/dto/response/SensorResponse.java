package io.github.justanaveragemax.sensorstatistics.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Model representing single sensor detailed information")
public record SensorResponse(
    @Schema(description = "Sensor type", example = "Temperature")
    String type
) {}

