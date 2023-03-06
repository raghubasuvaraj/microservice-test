package com.sta.cloudGatewayService.config;

import java.time.Duration;

import org.springframework.cloud.circuitbreaker.resilience4j.ReactiveResilience4JCircuitBreakerFactory;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JConfigBuilder;
import org.springframework.cloud.client.circuitbreaker.Customizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.timelimiter.TimeLimiterConfig;

/**
 * The integration between resilience4j loaded Circuit breaker registry and
 * spring cloud ReactiveResilience4JCircuitBreakerFactory.
 * 
 * @author r@ghu
 *
 */
@Configuration
public class ResilienceConfiguration {

	/**
	 * To enable circuit breaker built on top of Resilience4J we need to declare a
	 * Customizer bean that is passed a ReactiveResilience4JCircuitBreakerFactory.
	 * <p>
	 * The very simple configuration contains default circuit breaker settings and
	 * defines timeout duration using TimeLimiterConfig.
	 * 
	 * @return
	 */
	@Bean
	public Customizer<ReactiveResilience4JCircuitBreakerFactory> defaultCustomizer() {
		return factory -> factory.configureDefault(id -> new Resilience4JConfigBuilder(id)
				.circuitBreakerConfig(CircuitBreakerConfig.ofDefaults())
				.timeLimiterConfig(TimeLimiterConfig.custom()
						.timeoutDuration(Duration.ofMillis(200))
						.build())
				.build());
	}

}
