package edu.bu.studentapi.config;

import lombok.RequiredArgsConstructor;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
@RequiredArgsConstructor
@Configuration
public class SecurityConfig {

    private static final String[] WHITE_LIST_URLS = {
            "/h2-console/**",
            "/v2/api-docs",
            "/swagger-ui.html",
            "swagger-resources/**",
            "/configuration/ui",
            "/v3/api-docs/**",
            "/swagger-ui/**"
    };

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.csrf().disable().cors().and().authorizeHttpRequests()
                .requestMatchers(WHITE_LIST_URLS).permitAll()
                .requestMatchers(PathRequest.toH2Console()).permitAll()
                .requestMatchers("/api/courseManagement/**").hasAnyAuthority("ROLE_STUDENT","ROLE_ADMIN")
                .requestMatchers("api/student/**").authenticated()
                .requestMatchers("api/instructor/**").hasAnyAuthority("ROLE_INSTRUCTOR", "ROLE_ADMIN")
                .requestMatchers("api/course/create/**").hasAnyAuthority("ROLE_ADMIN")
                .requestMatchers("api/course/delete/**").hasAnyAuthority("ROLE_ADMIN")
                .anyRequest().authenticated()
                .and()
                .httpBasic();
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsService(){
        UserDetails adminDetails = User.builder()
                .username("admin")
                .password(passwordEncoder().encode("adminpass"))
                .authorities("ROLE_ADMIN")
                .build();
        UserDetails studentDetails = User.builder()
                .username("student")
                .password(passwordEncoder().encode("studentpass"))
                .authorities("ROLE_STUDENT")
                .build();
        UserDetails instructorDetails = User.builder()
                .username("instructor")
                .password(passwordEncoder().encode("instructorpass"))
                .authorities("ROLE_INSTRUCTOR")
                .build();
        return new InMemoryUserDetailsManager(adminDetails, studentDetails, instructorDetails);
    }

    @Bean
    WebSecurityCustomizer webSecurityCustomizer() {
        return web -> web.ignoring()
                .requestMatchers(new AntPathRequestMatcher("/h2-console/**"));
    }
}
