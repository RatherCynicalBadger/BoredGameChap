package personal.rathercynicalbadger.BoredGameChap.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {

    @Bean
    protected SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth -> auth
                        .requestMatchers("/", "/always-accessible/**", "/signup", "/about").permitAll()
                        .requestMatchers(antMatcher("/app/**")).hasRole("USER")
                        .requestMatchers(antMatcher("/group/admin/**")).hasRole("GROUPADMIN")
                        .requestMatchers(antMatcher("/admin-panel/**")).hasRole("SUPERADMIN")
//                        .anyRequest().authenticated()
                ).formLogin(login -> login.loginPage("/login").defaultSuccessUrl("/app/dashboard"));
        return http.build();
    }
}
