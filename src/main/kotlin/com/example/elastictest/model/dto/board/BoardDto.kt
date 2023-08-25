package com.example.elastictest.model.dto.board

import java.time.ZonedDateTime

data class BoardDto(
        var id: Long? = null,
        var title: String? = null,
        var content: String? = null,
        var created: ZonedDateTime? = null,

        var startDate: ZonedDateTime? = null,
        var endDate: ZonedDateTime? = null
)
