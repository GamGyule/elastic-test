package com.example.elastictest.config

import com.example.elastictest.config.model.type.QueryParam
import com.example.elastictest.util.Log
import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.annotation.Resource
import jakarta.servlet.http.HttpServletRequest
import org.springframework.core.MethodParameter
import org.springframework.stereotype.Component
import org.springframework.web.bind.support.WebDataBinderFactory
import org.springframework.web.context.request.NativeWebRequest
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.method.support.ModelAndViewContainer

@Component
class QueryStringArgumentResolver : HandlerMethodArgumentResolver {

    @Resource
    private lateinit var objectMapper: ObjectMapper

    override fun supportsParameter(parameter: MethodParameter): Boolean {
        return parameter.getParameterAnnotation(QueryParam::class.java) != null
    }

    override fun resolveArgument(parameter: MethodParameter, mavContainer: ModelAndViewContainer?, webRequest: NativeWebRequest, binderFactory: WebDataBinderFactory?): Any? {
        var request = webRequest.getNativeRequest(HttpServletRequest::class.java)

        var queryParam: MutableMap<String, Any> = mutableMapOf<String, Any>()
        for (param in request?.parameterMap?.keys!!) {
            if(request.getParameterValues(param).size == 1){
                queryParam[param] = request.getParameterValues(param)[0]
            }else{
                queryParam[param] = request.getParameterValues(param)
            }
        }

        var json = objectMapper.writeValueAsString(queryParam)
        Log.info(json)

        return objectMapper.readValue(json, parameter.parameterType)
    }
}