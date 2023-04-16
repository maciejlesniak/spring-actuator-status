package pl.sparkidea.springactuatorstatus.config;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.sparkidea.springactuatorstatus.service.FlipWorkingStatusService;

import java.util.stream.IntStream;

@Configuration
public class ActuatorConfig {

    @Bean
    HealthIndicator flipHealthIndicator(FlipWorkingStatusService service) {
        return () -> {
            var currentIndex = service.getCurrentIndex();
            var chain = service.getStatusesChain();
            var statusesChain = IntStream.range(0, chain.length).boxed().map(index ->
                    "%d -> %s %s".formatted(
                            index,
                            chain[index],
                            index == currentIndex ? "\uD83D\uDC4D" : ""
                    )).toArray();

            return new Health.Builder()
                    .withDetail("chain", statusesChain)
                    .status(service.getCurrentStatus())
                    .build();
        };
    }

}
