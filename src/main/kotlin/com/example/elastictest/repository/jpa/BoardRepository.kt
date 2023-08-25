package com.example.elastictest.repository.jpa

import com.example.elastictest.model.entity.board.Board
import org.springframework.data.jpa.repository.JpaRepository

interface BoardRepository: JpaRepository<Board, Long> {
}