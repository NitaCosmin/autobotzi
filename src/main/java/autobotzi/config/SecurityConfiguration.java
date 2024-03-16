package autobotzi.config;

import autobotzi.user.utils.Role;
import autobotzi.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final UserService userService;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(request ->
                        request
                                .requestMatchers("/auth/**").permitAll()
                                .requestMatchers("/admin/**").hasAnyAuthority(Role.ADMIN.name())
                                .requestMatchers("/user/**").hasAnyAuthority(Role.USER.name(),Role.ADMIN.name()
                                        ,Role.DEPARTMENT_MANAGER.name(),Role.PROJECT_MANAGER.name())
                                .requestMatchers("/departments/**").hasAnyAuthority(Role.ADMIN.name(),
                                        Role.DEPARTMENT_MANAGER.name())
                                .requestMatchers("/projects/**").hasAnyAuthority(Role.ADMIN.name(),
                                        Role.PROJECT_MANAGER.name(),Role.DEPARTMENT_MANAGER.name(),Role.USER.name())
                                .requestMatchers("/organizations/**").hasAnyAuthority(Role.ADMIN.name())
                                .requestMatchers("/roles/**").hasAnyAuthority(Role.ADMIN.name(),
                                        Role.DEPARTMENT_MANAGER.name())
                                .requestMatchers("/skills/**").hasAnyAuthority(Role.ADMIN.name(),
                                        Role.DEPARTMENT_MANAGER.name(),Role.PROJECT_MANAGER.name(),Role.USER.name())
                                .requestMatchers("/notifications/**").hasAnyAuthority(Role.ADMIN.name(),
                                        Role.DEPARTMENT_MANAGER.name(),Role.PROJECT_MANAGER.name(),Role.USER.name())
                                .requestMatchers("/mail/**").hasAnyAuthority(Role.ADMIN.name())
                                .requestMatchers("/swagger-ui/**", "/swagger-ui.html/**","/v3/api-docs/**").permitAll()
                                .anyRequest().authenticated()).sessionManagement(manager ->
                        manager
                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userService.userDetailsService());
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
}
