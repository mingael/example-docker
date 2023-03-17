package org.example.pdf.service

import com.itextpdf.text.*
import com.itextpdf.text.List
import com.itextpdf.text.pdf.PdfPCell
import com.itextpdf.text.pdf.PdfPTable
import org.example.pdf.util.PdfUtils
import org.springframework.core.io.ClassPathResource
import org.springframework.stereotype.Service

@Service
class PdfService {

    fun helloWorld() {
        val filename = "example_hello_world.pdf"
        PdfUtils.createPdf(
            filename, listOf(
                Paragraph("pdf file contents"),
                Paragraph("pdf file contents 2")
            )
        )
    }

    fun tablePdf() {
        val headers = listOf("A", "B", "C", "D")
        val rows = listOf(
            listOf("A1", "B1", "C1", "D1"),
            listOf("A2", "B2", "C2", "D2"),
            listOf("A3", "B3", "C3", "D3"),
        )

        val columnSize = 4 // 테이블 컬럼 수
        val table = PdfPTable(columnSize)
        table.widthPercentage = 80f // 테이블 가로 폭
        table.spacingBefore = 10f
        table.spacingAfter = 10f

        // 테이블 컬럼 폭
        val columnWidths = floatArrayOf(1f, 1f, 1f, 1f)
        table.setWidths(columnWidths)

        // 테이블 셀 - Header
        val headerFont = PdfUtils.getFont(12f)
        headers.forEach {
            val cell = PdfPCell()
            cell.phrase = Phrase(it, headerFont)
            table.addCell(cell)
        }

        // 테이블 셀 - Data
        val dataFont = PdfUtils.getFont(10f)
        rows.forEach { cols ->
            cols.forEach {
                val cell = PdfPCell(Phrase(it, dataFont))
                cell.borderColor = BaseColor.BLUE
                cell.paddingRight = 10f    // 여백
                cell.horizontalAlignment = Element.ALIGN_CENTER
                cell.verticalAlignment = Element.ALIGN_CENTER
                table.addCell(cell)
            }
            table.completeRow()
        }

        val cell1 = PdfPCell(Phrase("Cell11", dataFont))
        cell1.colspan = 2
        table.addCell(cell1)
        table.addCell(cell1)

        val filename = "example_table.pdf"
        PdfUtils.createPdf(filename, listOf(table))
    }

    fun listPdf() {
        val orderedList = List(List.ORDERED)
        orderedList.add(ListItem("ordered item 1"))
        orderedList.add(ListItem("ordered item 2"))

        val unorderedList = List(List.UNORDERED)
        unorderedList.add(ListItem("unordered item 1"))
        unorderedList.add(ListItem("unordered item 2"))

        val romanList = RomanList()
        romanList.add(ListItem("roman item 1"))
        romanList.add(ListItem("roman item 2"))

        val greekList = GreekList()
        greekList.add(ListItem("greek item 1"))
        greekList.add(ListItem("greek item 2"))

        val zapfDingbatsList = ZapfDingbatsList(43, 30)
        zapfDingbatsList.add(ListItem("zapf item 1"))
        zapfDingbatsList.add(ListItem("zapf item 2"))

        val nestedList = List(List.UNORDERED)
        val subList = List(true, false, 30f)
        subList.setListSymbol(Chunk("", FontFactory.getFont(FontFactory.HELVETICA, 6f)))
        subList.add("A")
        subList.add("B")
        nestedList.add(subList)
        subList.setListSymbol(Chunk("", FontFactory.getFont(FontFactory.HELVETICA, 6f)))
        subList.add("C")
        subList.add("D")
        nestedList.add(subList)

        val filename = "example_list.pdf"
        val lists = listOf(
            orderedList, unorderedList, romanList, greekList, zapfDingbatsList, nestedList
        )
        PdfUtils.createPdf(filename, lists)
    }

    fun imagePdf() {
        val srcImage = Image.getInstance(ClassPathResource("images/pikachu.jpg").uri.toString())
        srcImage.setAbsolutePosition(100f, 500f)
        srcImage.scaleAbsolute(200f, 200f)

//        val urlImage =
//            Image.getInstance(URL("https://static.wikia.nocookie.net/vsbattles/images/0/04/025Pikachu_XY_anime_4.png/revision/latest?cb=20180310153929"))

        val filename = "example_image"
        PdfUtils.createEncryptPdf(filename, listOf(srcImage), "1234")
    }

}