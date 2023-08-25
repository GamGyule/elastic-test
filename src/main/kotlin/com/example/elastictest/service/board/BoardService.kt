package com.example.elastictest.service.board

import com.example.elastictest.model.doc.board.BoardDoc
import com.example.elastictest.model.dto.board.BoardDto
import com.example.elastictest.model.entity.board.BoardMapper
import com.example.elastictest.repository.elastic.board.BoardERepository
import com.example.elastictest.repository.jpa.BoardRepository
import jakarta.annotation.Resource
import jakarta.transaction.Transactional
import org.slf4j.LoggerFactory
import org.springframework.data.domain.Pageable
import org.springframework.data.elasticsearch.core.SearchHitSupport
import org.springframework.data.elasticsearch.core.SearchPage
import org.springframework.stereotype.Service

@Service
@Transactional
class BoardService {
    @Resource
    private lateinit var boardRepository: BoardRepository
    @Resource
    private lateinit var boardMapper: BoardMapper
    @Resource
    private lateinit var boardERepository: BoardERepository

    val logger = LoggerFactory.getLogger(javaClass)

    fun store(boardDto: BoardDto): BoardDto {
        logger.info(boardDto.toString())
        val entity = boardRepository.save(boardMapper.map(boardDto))
        boardERepository.save(boardMapper.dmap(entity))
        return boardMapper.map(entity)
    }

    fun findAll(): MutableList<BoardDto> {
        val entities = boardRepository.findAll()
        return boardMapper.map(entities)
    }

    fun search(searchCondition: BoardDto, pageable: Pageable): Pair<MutableList<BoardDto>, SearchPage<BoardDoc>> {
        val search = boardERepository.search(searchCondition, pageable)
        val searchPage = SearchHitSupport.searchPageFor(search,pageable)

        val boardDocs: MutableList<BoardDoc> = ArrayList()
        for (searchHit in searchPage.content) {
            boardDocs.add(searchHit.content)
        }

        return Pair(boardMapper.dmap(boardDocs), searchPage)
    }
}