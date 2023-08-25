package com.example.elastictest.repository.elastic.board

import com.example.elastictest.model.doc.board.BoardDoc
import com.example.elastictest.model.dto.board.BoardDto
import org.springframework.data.domain.Pageable
import org.springframework.data.elasticsearch.core.SearchHits

interface BoardESearchRepository {
    fun search(searchCondition: BoardDto, pageable: Pageable): SearchHits<BoardDoc>
}