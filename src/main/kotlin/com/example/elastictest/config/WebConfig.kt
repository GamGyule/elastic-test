package com.example.elastictest.config

import jakarta.annotation.Resource
import org.springframework.context.annotation.Configuration
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class WebConfig : WebMvcConfigurer{
    @Resource
    private lateinit var queryStringArgumentResolver: QueryStringArgumentResolver

    override fun addArgumentResolvers(resolvers: MutableList<HandlerMethodArgumentResolver?>) {
        super.addArgumentResolvers(resolvers)
        resolvers.add(queryStringArgumentResolver)
    }
}