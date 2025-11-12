package com.example.falletterbackend.common.config.filter

import com.example.falletterbackend.common.error.ExceptionFilter
import com.example.falletterbackend.common.security.TokenFilter
import com.example.falletterbackend.common.security.TokenProvider
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.security.config.annotation.SecurityConfigurerAdapter
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.web.DefaultSecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.stereotype.Component

@Component
class FilterConfig(
    private val tokenProvider: TokenProvider,
    private val objectMapper: ObjectMapper
) : SecurityConfigurerAdapter<DefaultSecurityFilterChain?, HttpSecurity>() {

    override fun configure(builder: HttpSecurity) {
        val tokenFilter = TokenFilter(tokenProvider)
        val exceptionFilter = ExceptionFilter(objectMapper)
        builder.addFilterBefore(tokenFilter, UsernamePasswordAuthenticationFilter::class.java)
        builder.addFilterBefore(exceptionFilter, tokenFilter::class.java)
    }
}
