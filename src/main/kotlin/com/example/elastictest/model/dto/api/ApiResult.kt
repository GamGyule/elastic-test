package com.example.elastictest.model.dto.api

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonPropertyOrder

@JsonPropertyOrder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
data class ApiResult<T>(
        var message: String? = null,
        var code: ApiResultCode? = null,
        var payload: T? = null,
        var totalPage: Int? = null,
        var totalElement: Long? = null,
        var currentPage: Int? = null
)
