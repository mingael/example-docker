package com.example.tracker.common.constant

enum class Event(val value: String) {
    FIRST_VISIT("첫 방문"),
    PAGE_VIEW("화면 노출"),
    SESSION_START("세션 시작"),
    VIEW("노출"),
    USER_ENGAGEMENT("사용자가 사이트를 벗어남"),
    CLICK("클릭"),
    SCROLL("스크롤"), // 페이지 하단까지 90% 이동
    PURCHASE("구매"),
    LOGIN("로그인"),
    LOGOUT("로그아웃"),
    SIGN_UP("가입"),
}
/**
 * 광고 노출, 광고 클릭
 * 스크롤 노출, 스크롤 클릭
 */