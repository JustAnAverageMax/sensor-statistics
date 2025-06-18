package io.github.justanaveragemax.sensorstatistics.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import io.github.justanaveragemax.sensorstatistics.security.SecurityConstants;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignClientConfiguration implements RequestInterceptor {

  @Value("${clients.monitor-sensors.api-key}")
  private String apiKey;

  @Override
  public void apply(RequestTemplate requestTemplate) {
    requestTemplate.header(SecurityConstants.INTERNAL_API_HEADER, apiKey);

  }
}
