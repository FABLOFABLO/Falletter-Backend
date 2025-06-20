package com.example.falletterbackend.common.config

import com.example.falletterbackend.common.security.TokenProvider
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableWebSecurity
class SecurityConfig(
    private val objectMapper: ObjectMapper,
    private val tokenProvider: TokenProvider
) {
    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .csrf { it.disable() }
            .formLogin { it.disable() }
            .cors(Customizer.withDefaults())
            .sessionManagement { it.sessionCreationPolicy(SessionCreationPolicy.STATELESS) }

        http
            .authorizeHttpRequests { authorize ->
                authorize
                    // auth
                    .requestMatchers(HttpMethod.POST, "auth/email/verify").permitAll()
                    .requestMatchers(HttpMethod.POST, "auth/email/match").permitAll()

                    //user
                    .requestMatchers(HttpMethod.POST, "/user/signup").permitAll()
                    .requestMatchers(HttpMethod.POST, "/user/signin").permitAll()
                    .requestMatchers(HttpMethod.DELETE, "/user/logout").authenticated()
                    .requestMatchers(HttpMethod.GET, "/user/users").authenticated()

                    // community
                    .requestMatchers(HttpMethod.POST, "/community/posts").authenticated()
                    .requestMatchers(HttpMethod.GET, "community/posts").authenticated()
                    .requestMatchers(HttpMethod.GET, "/community/posts/{post-id}").authenticated()
                    .requestMatchers(HttpMethod.PATCH, "/community/posts/{post-id}").authenticated()
                    .requestMatchers(HttpMethod.DELETE, "/community/posts/{post-id}").authenticated()

                    // comment
                    .requestMatchers(HttpMethod.POST, "/comment/{post-id}").authenticated()
                    .requestMatchers(HttpMethod.DELETE, "/comment/{comment-id}").authenticated()

                    // brick
                    .requestMatchers(HttpMethod.GET, "/brick/count").authenticated()

                    // letter
                    .requestMatchers(HttpMethod.GET, "/letter/count").authenticated()

                    // question
                    .requestMatchers(HttpMethod.GET, "/question/all").authenticated()

                    .anyRequest().denyAll()
            }

        http.with(FilterConfig(tokenProvider, objectMapper)) {}

        return http.build()
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder = BCryptPasswordEncoder()
}