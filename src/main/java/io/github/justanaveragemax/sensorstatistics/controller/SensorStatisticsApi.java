package io.github.justanaveragemax.sensorstatistics.controller;

import io.github.justanaveragemax.sensorstatistics.dto.response.SensorStatisticsResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.time.LocalDate;
import org.springdoc.core.converters.models.PageableAsQueryParam;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/statistics")
@Tag(name = "Statistics", description = "Endpoints for getting sensor statistics")
public interface SensorStatisticsApi {

  @GetMapping
  @PageableAsQueryParam
  @Operation(
      summary = "Get paginated list of statistics",
      description = "Get list of statistics based on provided parameters"
  )
  ResponseEntity<PagedModel<SensorStatisticsResponse>> getStatistics(@RequestParam(required = false) @DateTimeFormat(iso = ISO.DATE) LocalDate startDate,
                                                                    @RequestParam(required = false) @DateTimeFormat(iso = ISO.DATE) LocalDate endDate,
                                                                    @Parameter(hidden = true) Pageable pageable);

  @GetMapping("/fetch")
  @Operation(
      summary = "Fetch sensor info from monitor-sensors service",
      description = "Fetch sensor info from monitor-sensors service immediately, regardless of configured scheduler"
  )
  ResponseEntity<Void> fetchStatistics();

}
