package org.genesiscode.practicesecurity.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import static org.genesiscode.practicesecurity.security.UserRole.*;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public SecurityConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/api/**").hasRole(STUDENT.name())
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
    }

    @Bean
    @Override
    protected UserDetailsService userDetailsService() {
        UserDetails maria = User.builder()
                .username("maria")
                .password(passwordEncoder.encode("maria17"))
                .roles(STUDENT.name()) //ROLE_STUDENT
                .build();

        UserDetails jose = User.builder()
                .username("jose")
                .password(passwordEncoder.encode("jose17"))
                .roles(ADMIN.name()) //ROLE_ADMIN
                .build();

        UserDetails sara = User.builder()
                .username("sara")
                .password(passwordEncoder.encode("sara17"))
                .roles(TRAINER.name()) //ROLE_TRAINER
                .build();

        return new InMemoryUserDetailsManager(maria, jose, sara);
    }
}
