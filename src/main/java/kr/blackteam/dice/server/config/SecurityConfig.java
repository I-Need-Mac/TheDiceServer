package kr.blackteam.dice.server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    // @formatter:off
    http.csrf(AbstractHttpConfigurer::disable)
        .cors(AbstractHttpConfigurer::disable)
        .authorizeHttpRequests(auth ->
            auth.requestMatchers("/api/swagger-ui/**").permitAll()
                .requestMatchers("/api/docs/**").permitAll()
                .requestMatchers("/api/actuator/prometheus").permitAll()
                .requestMatchers("/api/v1/**").permitAll()
                .anyRequest().authenticated()
        )
    ;
    // @formatter:on
    return http.build();
  }

}
