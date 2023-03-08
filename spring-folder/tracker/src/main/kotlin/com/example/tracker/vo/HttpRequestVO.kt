package com.example.tracker.vo

import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.HttpHeaders

data class HttpRequestVO(
    val auth: AuthVO,
    val requestedSessionId: String?,
    val remote: RemoteVO,
    val request: RequestVO,
    val target: TargetVO
) {
    data class AuthVO(
        val type: String?,
        val authorization: String?
    )

    data class RemoteVO(
        val user: String?,
        val ip: String?,
        val host: String?,
        val port: Int?
    )

    data class RequestVO(
        val id: String?,
        // 프로토콜 + 도메인 + 포트번호 + 컨텍스트 경로 + 서블릿 경로
        val url: String?,
        // 컨텍스트 경로 + 서블릿 경로
        val uri: String?,
        val pathInfo: String?,
        val method: String?,
        val urlDetail: UrlDetailVO
    ) {
        data class UrlDetailVO(
            val protocol: String?,
            val domain: String?,
            val port: Int,
            val contextPath: String?,
            val servletPath: String?,
            val queryString: String?
        )
    }

    data class TargetVO(
        val host: String?,
        val ip: String?,
        val port: Int?
    )

    companion object {
        fun of(request: HttpServletRequest): HttpRequestVO {
            val authorization = (request.getHeader(HttpHeaders.AUTHORIZATION) ?: "").split(" ")
            return HttpRequestVO(
                auth = AuthVO(
                    type = authorization.firstOrNull(),
                    authorization = authorization.lastOrNull()
                ),
                requestedSessionId = request.requestedSessionId,
                remote = RemoteVO(
                    user = request.remoteUser,
                    ip = request.remoteAddr,
                    host = request.remoteHost,
                    port = request.remotePort
                ),
                request = RequestVO(
                    id = request.requestId,
                    url = request.requestURL.toString(),
                    uri = request.requestURI,
                    pathInfo = request.pathInfo,
                    urlDetail = RequestVO.UrlDetailVO(
                        protocol = request.protocol,
                        domain = request.serverName,
                        port = request.serverPort,
                        queryString = request.queryString,
                        contextPath = request.contextPath,
                        servletPath = request.servletPath,

                        ),
                    method = request.method,
                ),
                target = TargetVO(
                    ip = request.localAddr,
                    host = request.localName,
                    port = request.localPort
                )
            )
        }
    }
}