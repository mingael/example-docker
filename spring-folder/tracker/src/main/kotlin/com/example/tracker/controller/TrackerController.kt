package com.example.tracker.controller

import com.example.tracker.vo.HttpRequestVO
import com.google.gson.Gson
import jakarta.servlet.http.HttpServletRequest
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/tracker/api")
class TrackerController {

    companion object {
        private val logger = LoggerFactory.getLogger(this::class.java)
    }

    // http://localhost:8080/tracker/api
    @GetMapping
    fun tracking(
        request: HttpServletRequest,
        @RequestParam category: String?,
        @RequestParam event: String?,
    ): ResponseEntity<Any> {
        val req = Gson().toJson(HttpRequestVO.of(request))
        logger.debug(req)
        logger.debug("${Gson().fromJson(req, HttpRequestVO::class.java)}")
        return ResponseEntity(HttpStatus.OK)
    }

}