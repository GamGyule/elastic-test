package com.example.elastictest.model.entity.board

import com.example.elastictest.model.doc.board.BoardDoc
import com.example.elastictest.model.dto.board.BoardDto
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.ReportingPolicy

@Mapper(componentModel = "spring"
, unmappedTargetPolicy = ReportingPolicy.IGNORE)
interface BoardMapper {

    @Mapping(target = "created", ignore = true)
    fun map(dto: BoardDto): Board
    fun map(entity: Board): BoardDto
    fun map(entities: MutableList<Board>): MutableList<BoardDto>


    @Mapping(target = "created", ignore = true)
    fun dmap(dto: BoardDto): BoardDoc
    fun dmap(doc: BoardDoc): Board
    fun dmap(docs: MutableList<BoardDoc>): MutableList<BoardDto>

    fun dmap(entity: Board): BoardDoc
}