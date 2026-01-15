package com.example.falletterbackend.common.config.security

import com.example.falletterbackend.common.config.filter.FilterConfig
import com.example.falletterbackend.common.security.TokenProvider
import com.example.falletterbackend.common.security.handler.CustomAccessDeniedHandler
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
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer
import org.springframework.security.web.SecurityFilterChain
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource

@Configuration
@EnableWebSecurity
class SecurityConfig(
    private val objectMapper: ObjectMapper,
    private val tokenProvider: TokenProvider,
    private val accessDeniedHandler: CustomAccessDeniedHandler
) {
    companion object {
        private val SWAGGER_WHITELIST = arrayOf(
            "/swagger-ui/**",
            "/swagger-ui.html",
            "/v3/api-docs/**",
            "/v3/api-docs",
            "/swagger-resources/**",
            "/webjars/**"
        )
    }

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .csrf { it.disable() }
            .formLogin { it.disable() }
            .cors(Customizer.withDefaults())
            .sessionManagement { it.sessionCreationPolicy(SessionCreationPolicy.STATELESS) }
            .exceptionHandling { it.accessDeniedHandler(accessDeniedHandler) }

        http
            .authorizeHttpRequests { authorize ->
                authorize
                    // swagger
                    .requestMatchers(*SWAGGER_WHITELIST).permitAll()

                    // fcm
                    .requestMatchers(HttpMethod.POST, "/fcm/send").hasRole("USER")

                    // device token
                    .requestMatchers(HttpMethod.POST, "/device/token").hasRole("USER")
                    .requestMatchers(HttpMethod.DELETE, "/device/token/{device-id}").hasRole("USER")

                    // auth
                    .requestMatchers(HttpMethod.POST, "/auth/email/verify").permitAll()
                    .requestMatchers(HttpMethod.POST, "/auth/email/match").permitAll()

                    // user
                    .requestMatchers(HttpMethod.POST, "/user/signup").permitAll()
                    .requestMatchers(HttpMethod.POST, "/user/signin").permitAll()
                    .requestMatchers(HttpMethod.DELETE, "/user/logout").hasRole("USER")
                    .requestMatchers(HttpMethod.GET, "/user/users").hasRole("USER")
                    .requestMatchers(HttpMethod.GET, "/user/student").hasRole("USER")

                    // community
                    .requestMatchers(HttpMethod.POST, "/community/posts").hasRole("USER")
                    .requestMatchers(HttpMethod.GET, "/community/posts").hasRole("USER")
                    .requestMatchers(HttpMethod.GET, "/community/posts/{post-id}").hasRole("USER")
                    .requestMatchers(HttpMethod.PATCH, "/community/posts/{post-id}").hasRole("USER")
                    .requestMatchers(HttpMethod.DELETE, "/community/posts/{post-id}").hasRole("USER")

                    // comment
                    .requestMatchers(HttpMethod.POST, "/comment/{post-id}").hasRole("USER")
                    .requestMatchers(HttpMethod.DELETE, "/comment/{comment-id}").hasRole("USER")

                    // item
                    .requestMatchers(HttpMethod.GET, "/item/letter/count").hasRole("USER")
                    .requestMatchers(HttpMethod.PATCH, "/item/letter/update").hasRole("USER")
                    .requestMatchers(HttpMethod.GET, "/item/brick/count").hasRole("USER")
                    .requestMatchers(HttpMethod.PATCH, "/item/brick/update").hasRole("USER")

                    // history
                    .requestMatchers(HttpMethod.POST, "/history/brick/save").hasRole("USER")
                    .requestMatchers(HttpMethod.GET, "/history/brick/used").hasRole("USER")

                    // answer
                    .requestMatchers(HttpMethod.POST, "/answer/choose").hasRole("USER")
                    .requestMatchers(HttpMethod.GET, "/answer/chosen").hasRole("USER")

                    // letter
                    .requestMatchers(HttpMethod.POST, "/letter/sent").hasRole("USER")
                    .requestMatchers(HttpMethod.GET, "/letter/sent/{letter-id}").hasRole("USER")
                    .requestMatchers(HttpMethod.GET, "/letter/sent/all").hasRole("USER")
                    .requestMatchers(HttpMethod.GET, "/letter/received/{letter-id}").hasRole("USER")
                    .requestMatchers(HttpMethod.GET, "/letter/received/all").hasRole("USER")

                    // question
                    .requestMatchers(HttpMethod.GET, "/question/all").hasRole("USER")

                    // hint
                    .requestMatchers(HttpMethod.POST, "/hint/save").hasRole("USER")
                    .requestMatchers(HttpMethod.GET, "/hint/{answer-id}").hasRole("USER")
                    .requestMatchers(HttpMethod.PATCH, "/hint/update").hasRole("USER")

                    // timer
                    .requestMatchers(HttpMethod.POST, "/timer/brick").hasRole("USER")
                    .requestMatchers(HttpMethod.GET, "/timer/brick").hasRole("USER")
                    .requestMatchers(HttpMethod.POST, "/timer/roulette").hasRole("USER")
                    .requestMatchers(HttpMethod.GET, "/timer/roulette").hasRole("USER")

                    // suspend
                    .requestMatchers(HttpMethod.GET, "/suspend/all").hasRole("USER")
                    .requestMatchers(HttpMethod.GET, "/suspend/{suspend-id}").hasRole("USER")

                    // image
                    .requestMatchers(HttpMethod.POST, "/image/upload").hasRole("USER")

                    // notice (User도 조회 가능)
                    .requestMatchers(HttpMethod.GET, "/notice").authenticated()
                    .requestMatchers(HttpMethod.GET, "/notice/{notice-id}").authenticated()

                    // admin letter
                    .requestMatchers(HttpMethod.GET, "/admin/letter/unpassed").hasAnyRole("ADMIN", "SUPER_ADMIN")
                    .requestMatchers(HttpMethod.GET, "/admin/letter/unpassed/{letter-id}").hasAnyRole("ADMIN", "SUPER_ADMIN")

                    // admin notice
                    .requestMatchers(HttpMethod.POST, "/admin/notice").hasAnyRole("ADMIN", "SUPER_ADMIN")
                    .requestMatchers(HttpMethod.GET, "/admin/notice").hasAnyRole("ADMIN", "SUPER_ADMIN")
                    .requestMatchers(HttpMethod.GET, "/admin/notice/{notice-id}").hasAnyRole("ADMIN", "SUPER_ADMIN")
                    .requestMatchers(HttpMethod.DELETE, "/admin/notice/{notice-id}").hasAnyRole("ADMIN", "SUPER_ADMIN")

                    // admin community
                    .requestMatchers(HttpMethod.PATCH, "/admin/community/{community-id}").hasAnyRole("ADMIN", "SUPER_ADMIN")

                    // admin user
                    .requestMatchers(HttpMethod.GET, "/admin/user/all").hasAnyRole("ADMIN", "SUPER_ADMIN")
                    .requestMatchers(HttpMethod.GET, "/admin/user/{user-id}").hasAnyRole("ADMIN", "SUPER_ADMIN")

                    // admin suspend
                    .requestMatchers(HttpMethod.POST, "/admin/warning/{user-id}").hasAnyRole("ADMIN", "SUPER_ADMIN")
                    .requestMatchers(HttpMethod.POST, "/admin/block/{user-id}").hasAnyRole("ADMIN", "SUPER_ADMIN")

                    // admin auth
                    .requestMatchers(HttpMethod.POST, "/admin/auth/signup").permitAll()
                    .requestMatchers(HttpMethod.POST, "/admin/auth/signin").permitAll()

                    // super admin
                    .requestMatchers(HttpMethod.GET, "/super-admin/requests").hasRole("SUPER_ADMIN")
                    .requestMatchers(HttpMethod.PATCH, "/super-admin/approve/{admin-id}").hasRole("SUPER_ADMIN")
                    .requestMatchers(HttpMethod.PATCH, "/super-admin/reject/{admin-id}").hasRole("SUPER_ADMIN")
                    .requestMatchers(HttpMethod.GET, "/super-admin/admins").hasRole("SUPER_ADMIN")


                    // notification
                    .requestMatchers(HttpMethod.GET, "/notification/setting").hasAnyRole("USER", "ADMIN")
                    .requestMatchers(HttpMethod.PATCH, "/notification/setting").hasAnyRole("USER", "ADMIN")

                    .anyRequest().hasAnyRole("USER", "ADMIN")

                    .anyRequest().authenticated()

            }

        http.with(FilterConfig(tokenProvider, objectMapper)) {}

        return http.build()
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder = BCryptPasswordEncoder()

    @Bean
    fun webSecurityCustomizer(): WebSecurityCustomizer {
        return WebSecurityCustomizer { web ->
            web.ignoring().requestMatchers(*SWAGGER_WHITELIST)
        }
    }

    @Bean
    fun corsConfigurationSource(): CorsConfigurationSource {
        val configuration = CorsConfiguration()

        configuration.allowedOrigins = listOf(
            "https://falletter.co.kr",
            "http://falletter.co.kr",
            "http://localhost:5173"
        )

        configuration.allowedMethods = listOf(
            "GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"
        )
        configuration.allowedHeaders = listOf("*")
        configuration.allowCredentials = true
        configuration.maxAge = 3600L

        val source = UrlBasedCorsConfigurationSource()
        source.registerCorsConfiguration("/**", configuration)
        return source
    }
}
