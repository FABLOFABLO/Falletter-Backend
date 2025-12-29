package com.example.falletterbackend.common.config.security

import com.example.falletterbackend.common.config.filter.FilterConfig
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
                    .requestMatchers(HttpMethod.DELETE, "/user/logout").hasAnyRole("USER", "ADMIN")
                    .requestMatchers(HttpMethod.GET, "/user/users").hasAnyRole("USER", "ADMIN")
                    .requestMatchers(HttpMethod.GET, "/user/student").hasAnyRole("USER", "ADMIN")

                    // community
                    .requestMatchers(HttpMethod.POST, "/community/posts").hasAnyRole("USER", "ADMIN")
                    .requestMatchers(HttpMethod.GET, "community/posts").hasAnyRole("USER", "ADMIN")
                    .requestMatchers(HttpMethod.GET, "/community/posts/{post-id}").hasAnyRole("USER", "ADMIN")
                    .requestMatchers(HttpMethod.PATCH, "/community/posts/{post-id}").hasAnyRole("USER", "ADMIN")
                    .requestMatchers(HttpMethod.DELETE, "/community/posts/{post-id}").hasAnyRole("USER", "ADMIN")

                    // comment
                    .requestMatchers(HttpMethod.POST, "/comment/{post-id}").hasAnyRole("USER", "ADMIN")
                    .requestMatchers(HttpMethod.DELETE, "/comment/{comment-id}").hasAnyRole("USER", "ADMIN")

                    // item
                    .requestMatchers(HttpMethod.GET, "/item/letter/count").hasAnyRole("USER", "ADMIN")
                    .requestMatchers(HttpMethod.PATCH, "/item/letter/update").hasAnyRole("USER", "ADMIN")
                    .requestMatchers(HttpMethod.GET, "/item/brick/count").hasAnyRole("USER", "ADMIN")
                    .requestMatchers(HttpMethod.PATCH, "/item/brick/update").hasAnyRole("USER", "ADMIN")

                    // history
                    .requestMatchers(HttpMethod.POST, "/history/brick/save").hasAnyRole("USER", "ADMIN")
                    .requestMatchers(HttpMethod.GET, "/history/brick/used").hasAnyRole("USER", "ADMIN")

                    // answer
                    .requestMatchers(HttpMethod.POST, "/answer/choose").hasAnyRole("USER", "ADMIN")
                    .requestMatchers(HttpMethod.GET, "/answer/chosen").hasAnyRole("USER", "ADMIN")

                    // letter
                    .requestMatchers(HttpMethod.POST, "/letter/sent").hasAnyRole("USER", "ADMIN")
                    .requestMatchers(HttpMethod.GET, "/letter/sent/{letter-id}").hasAnyRole("USER", "ADMIN")
                    .requestMatchers(HttpMethod.GET, "/letter/sent/all").hasAnyRole("USER", "ADMIN")
                    .requestMatchers(HttpMethod.GET, "/letter/received/{letter-id}").hasAnyRole("USER", "ADMIN")
                    .requestMatchers(HttpMethod.GET, "/letter/received/all").hasAnyRole("USER", "ADMIN")

                    // question
                    .requestMatchers(HttpMethod.GET, "/question/all").hasAnyRole("USER", "ADMIN")

                    // hint
                    .requestMatchers(HttpMethod.POST, "/hint/save").hasAnyRole("USER", "ADMIN")
                    .requestMatchers(HttpMethod.GET, "/hint/{answer-id}").hasAnyRole("USER", "ADMIN")
                    .requestMatchers(HttpMethod.PATCH, "/hint/update").hasAnyRole("USER", "ADMIN")

                    .anyRequest().hasAnyRole("USER", "ADMIN")
            }

        http.with(FilterConfig(tokenProvider, objectMapper)) {}

        return http.build()
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder = BCryptPasswordEncoder()
}
