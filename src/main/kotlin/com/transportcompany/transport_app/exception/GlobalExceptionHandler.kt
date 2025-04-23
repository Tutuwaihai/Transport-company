package com.transportcompany.transport_app.exception

import com.transportcompany.transport_app.dto.ApiResponse
import feign.FeignException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class GlobalExceptionHandler {
    @ExceptionHandler(FeignException.Unauthorized::class)
    fun handleUnauthorized(e: FeignException.Unauthorized): ResponseEntity<ApiResponse<Any>> {
        return ResponseEntity(
            ApiResponse(
                code = 401,
                status = HttpStatus.UNAUTHORIZED,
                message = "Неверные учетные данные"
            ),
            HttpStatus.UNAUTHORIZED
        )
    }

    @ExceptionHandler(FeignException.Forbidden::class)
    fun handleForbidden(e: FeignException.Forbidden): ResponseEntity<ApiResponse<Any>> {
        return ResponseEntity(
            ApiResponse(
                code = 403,
                status = HttpStatus.FORBIDDEN,
                message = "Доступ запрещен или пользователь не найден"
            ),
            HttpStatus.FORBIDDEN
        )
    }

    @ExceptionHandler(FeignException::class)
    fun handleGenericFeign(e: FeignException): ResponseEntity<ApiResponse<Any>> {
        return ResponseEntity(
            ApiResponse(
                code = e.status(),
                status = HttpStatus.resolve(e.status()) ?: HttpStatus.INTERNAL_SERVER_ERROR,
                message = "Ошибка при обращении к внешнему API"
            ),
            HttpStatus.resolve(e.status()) ?: HttpStatus.INTERNAL_SERVER_ERROR
        )
    }

}