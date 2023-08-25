package com.example.elastictest.repository.elastic.board

import co.elastic.clients.elasticsearch._types.query_dsl.Query
import co.elastic.clients.elasticsearch._types.query_dsl.QueryBuilders
import com.example.elastictest.model.doc.board.BoardDoc
import com.example.elastictest.model.dto.board.BoardDto
import org.springframework.data.domain.Pageable
import org.springframework.data.elasticsearch.client.elc.NativeQuery
import org.springframework.data.elasticsearch.core.ElasticsearchOperations
import org.springframework.data.elasticsearch.core.SearchHits

class BoardESearchRepositoryImpl(private val operations: ElasticsearchOperations) : BoardESearchRepository {

    override fun search(searchCondition: BoardDto, pageable: Pageable): SearchHits<BoardDoc> {
        val nativeQuery = NativeQuery.builder()
                .withQuery(setCondition(searchCondition))
                .withPageable(pageable)
                .build()

        return operations.search(nativeQuery, BoardDoc::class.java)

    }

    private fun setCondition(searchCondition: BoardDto): Query {

        val shouldClauses = mutableListOf<Query>()

        if (searchCondition.title != null) {
            shouldClauses.add(QueryBuilders.match { t -> t.field("title").query(searchCondition.title) })
        }

        if (searchCondition.content != null) {
            shouldClauses.add(QueryBuilders.match { t -> t.field("content").query(searchCondition.content) })
        }

        return QueryBuilders.bool()
                .should(shouldClauses)
                .build()._toQuery()
    }
}