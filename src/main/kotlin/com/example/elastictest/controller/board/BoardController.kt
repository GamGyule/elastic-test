package com.example.elastictest.controller.board

import com.example.elastictest.config.model.type.QueryParam
import com.example.elastictest.model.dto.api.ApiResult
import com.example.elastictest.model.dto.api.ApiResultCode
import com.example.elastictest.model.dto.board.BoardDto
import com.example.elastictest.service.board.BoardService
import jakarta.annotation.Resource
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RequestMapping("/api/v1/board")
@RestController
class BoardController {
    @Resource
    private lateinit var boardService: BoardService

    @GetMapping
    fun getAll(): ResponseEntity<ApiResult<MutableList<BoardDto>>> {
        val result = ApiResult(code = ApiResultCode.succeed, payload = boardService.findAll())
        return ResponseEntity(result, HttpStatus.OK)
    }

    @GetMapping("/search")
    fun search(@QueryParam searchCondition: BoardDto, pageable: Pageable): ResponseEntity<ApiResult<MutableList<BoardDto>>> {
        val search = boardService.search(searchCondition, pageable)

        val result = ApiResult(
                code = ApiResultCode.succeed,
                payload = search.first,
                totalElement = search.second.totalElements,
                totalPage = search.second.totalPages,
                currentPage = search.second.number
        )
        return ResponseEntity(result, HttpStatus.OK)
    }

    @PostMapping
    fun save(@RequestBody boardDto: BoardDto): ResponseEntity<ApiResult<BoardDto>> {
        val result = ApiResult(code = ApiResultCode.succeed, payload = boardService.store(boardDto))
        return ResponseEntity(result, HttpStatus.OK)
    }
}