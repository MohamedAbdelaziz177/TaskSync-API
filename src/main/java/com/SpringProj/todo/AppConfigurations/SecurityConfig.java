package com.SpringProj.todo.AppConfigurations;

import com.SpringProj.todo.Filters.JwtAuthFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.config.oauth2.client.CommonOAuth2Provider;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final UserDetailsService userDetailsService;

    private final JwtAuthFilter jwtAuthFilter;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.csrf(csrf -> csrf.disable())
                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(reqs -> reqs
                        .requestMatchers("/api/auth/register").permitAll()
                        .requestMatchers("/api/auth/login").permitAll()
                        .requestMatchers("/api/auth/confirm-email").permitAll()
                        .requestMatchers("/api/auth/resend-confirmation-code").permitAll()
                        .requestMatchers("api/auth/refresh-token").permitAll()
                      //  .requestMatchers(HttpMethod.GET, "/api/demo/sayHello").permitAll()
                        .anyRequest().authenticated()

                )
                .formLogin(Customizer.withDefaults())
                .oauth2Login(Customizer.withDefaults())
                .oauth2Client(Customizer.withDefaults())
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {

        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager() {
        return new ProviderManager(authenticationProvider());
    }

    @Bean
    ClientRegistrationRepository clientRegistrationRepository()
    {
        ClientRegistration github = githubClientRegistration();
        //ClientRegistration facebook = facebookClientRegistration();
        ClientRegistration google = googleClientRegistration();

        return new InMemoryClientRegistrationRepository(github, google);
    }

     //private ClientRegistration facebookClientRegistration() {
 //
     //    return CommonOAuth2Provider
     //            .FACEBOOK.getBuilder("facebook")
     //            .clientId("")
     //            .clientSecret("").build();
     //}

    private ClientRegistration googleClientRegistration() {
        return CommonOAuth2Provider
                .GOOGLE.getBuilder("google")
                .clientId("909998816664-62t4b6uel3vtscmb720jck8js54dinjl.apps.googleusercontent.com")
                .clientSecret("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").build();
    }

    private ClientRegistration githubClientRegistration() {

        return CommonOAuth2Provider.GITHUB.getBuilder("github")
                .clientId("Ov23liInL0K0p9W2VoVk")
                .clientSecret("398a53bb5ae1b400005542c4e90bd6a29c6789e8").build();
    }


}
