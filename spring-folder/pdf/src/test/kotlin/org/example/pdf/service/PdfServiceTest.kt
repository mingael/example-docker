package org.example.pdf.service

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
internal class PdfServiceTest @Autowired constructor(
    private val pdfService: PdfService
) {

    @Test
    fun createPdfTest() {
        pdfService.helloWorld()
    }

    @Test
    fun createTablePdfTest() {
        pdfService.tablePdf()
    }

    @Test
    fun createListPdfTest() {
        pdfService.listPdf()
    }

    @Test
    fun createImagePdfTest() {
        pdfService.imagePdf()
    }

}