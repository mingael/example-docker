package com.example.aspect

import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.springframework.stereotype.Component

@Component
@Aspect
class PerfAspect {

    @Around(value = "bean(eventServiceImpl)")
    fun perfPref(joinPoint: ProceedingJoinPoint): Any? {
        val startTime = System.currentTimeMillis()
        val proceed = joinPoint.proceed()
        println("executionTime: ${System.currentTimeMillis() - startTime}")
        return proceed
    }


    @Around(value = "@annotation(com.example.annotation.PerfLogging)")
    fun logPref(joinPoint: ProceedingJoinPoint): Any? {
        val proceed = joinPoint.proceed()
        println(" owo ")
        return proceed
    }

}