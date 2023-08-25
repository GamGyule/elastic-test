package com.example.elastictest.repository.elastic.board

import com.example.elastictest.model.doc.board.BoardDoc
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository
import java.time.ZonedDateTime

interface BoardERepository : ElasticsearchRepository<BoardDoc, Long>, BoardESearchRepository {
    fun findByTitleContains(title: String): MutableList<BoardDoc>

    fun findByContentContains(content: String): MutableList<BoardDoc>

    fun findByCreatedBetween(start: ZonedDateTime, end: ZonedDateTime): MutableList<BoardDoc>
}