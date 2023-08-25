package com.example.elastictest.config

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

class ZonedDateTimeDeserializer : JsonDeserializer<ZonedDateTime>() {
    override fun deserialize(p: JsonParser?, ctxt: DeserializationContext?): ZonedDateTime {
        return ZonedDateTime.parse(p?.getValueAsString(), DateTimeFormatter.ISO_OFFSET_DATE_TIME)
    }
}