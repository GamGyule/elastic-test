package com.example.elastictest.controller

import jakarta.servlet.http.HttpSession
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RequestMapping("/api/v1/home")
@RestController
class HomeController {

    @GetMapping
    fun check(session: HttpSession): ResponseEntity<String> {
        return ResponseEntity(session.id.toString(), HttpStatus.OK)
    }
}