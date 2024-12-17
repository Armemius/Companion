package porunit.comp.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import porunit.comp.data.domain.Role;
import porunit.comp.jwt.JwtAuthEntryPoint;
import porunit.comp.jwt.JwtAuthFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

  private final JwtAuthEntryPoint authEntryPoint;
  private final JwtAuthFilter jwtAuthFilter;

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    return http
            .csrf(AbstractHttpConfigurer::disable) // CSRF отключен
            .exceptionHandling(exception -> exception
                    .authenticationEntryPoint(authEntryPoint)
            )
            .sessionManagement(session -> session
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // Stateless сессии
            )
            .authorizeHttpRequests(auth -> auth
                    .requestMatchers(new AntPathRequestMatcher("/ai/generate"),
                            new AntPathRequestMatcher("/orders/save")).authenticated()
                    .requestMatchers(new AntPathRequestMatcher("/admin/**")).hasAuthority(Role.ADMIN.name())
                    .anyRequest().permitAll() // Остальные запросы разрешены
            )
            .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class) // JWT фильтр
            .build();
  }

  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
    return config.getAuthenticationManager();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}