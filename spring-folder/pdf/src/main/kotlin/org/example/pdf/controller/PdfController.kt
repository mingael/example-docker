package org.example.pdf.controller

import org.example.pdf.service.PdfService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class PdfController(
    private val pdfService: PdfService
) {

    @PostMapping("/hello")
    fun createPdf() {
        pdfService.helloWorld()
    }

}