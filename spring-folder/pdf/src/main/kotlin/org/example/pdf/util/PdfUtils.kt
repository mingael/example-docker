package org.example.pdf.util

import com.itextpdf.text.Document
import com.itextpdf.text.Element
import com.itextpdf.text.Font
import com.itextpdf.text.pdf.BaseFont
import com.itextpdf.text.pdf.PdfWriter
import java.io.File
import java.io.FileOutputStream

class PdfUtils {
    companion object {

        private const val OWNER_PASSWORD = "root"

        /**
         * PDF 생성
         *
         * @param filename: 파일명
         * @param contentsList: 내용
         */
        fun createPdf(filename: String, contentsList: List<Element>) {
            val document = Document()
            try {
                PdfWriter.getInstance(document, FileOutputStream(File(filename)))
                document.open()
                contentsList.forEach { document.add(it) }
                fileAttributeSetting(document)
            } catch (e: Exception) {
                throw IllegalStateException("PDF ERROR ${e.printStackTrace()}")
            } finally {
                document.close()
            }
        }

        /**
         * 암호화된 PDF 생성
         *
         * @param filename: 파일명
         * @param contentsList: 내용
         * @param userPassword: 암호
         */
        fun createEncryptPdf(filename: String, contentsList: List<Element>, userPassword: String) {
            val document = Document()
            try {
                val writer = PdfWriter.getInstance(document, FileOutputStream(File(filename)))
                writer.setEncryption(
                    userPassword.toByteArray(),
                    OWNER_PASSWORD.toByteArray(),
                    PdfWriter.ALLOW_PRINTING,
                    PdfWriter.ENCRYPTION_AES_256
                )
                writer.open()
                document.open()

                contentsList.forEach { document.add(it) }
                fileAttributeSetting(document)

                document.close()
                writer.close()
            } catch (e: Exception) {
                throw IllegalStateException("Encrypt PDF ERROR ${e.printStackTrace()}")
            }
        }

        /**
         * 글꼴
         *
         * @param fontSize: 글꼴 크기
         */
        fun getFont(fontSize: Float): Font {
            val baseFont = BaseFont.createFont("fonts/malgun.ttf", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED)
            return Font(baseFont, fontSize)
        }

        /**
         * 파일 속성 설정
         */
        private fun fileAttributeSetting(doc: Document) {
            doc.addAuthor("MinK")
            doc.addCreationDate()
            doc.addCreator("dico.me")
            doc.addTitle("MY PDF")
            doc.addSubject("PDF SPRING")
        }

    }
}