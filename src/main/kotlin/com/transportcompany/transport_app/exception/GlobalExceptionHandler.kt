package com.transportcompany.transport_app.exception

import com.transportcompany.transport_app.dto.ApiResponse
import feign.FeignException
import jakarta.persistence.EntityNotFoundException
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException
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

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleValidationException(ex: MethodArgumentNotValidException): ResponseEntity<ApiResponse<Any>> {
        val errors = ex.bindingResult.allErrors
            .filterIsInstance<FieldError>()
            .associate { it.field to (it.defaultMessage ?: "Invalid value") }

        return ResponseEntity(
            ApiResponse(
                code = 400,
                status = HttpStatus.BAD_REQUEST,
                message = "Ошибка валидации данных",
                data = errors
            ),
            HttpStatus.BAD_REQUEST
        )
    }

    @ExceptionHandler(EntityNotFoundException::class)
    fun handleEntityNotFound(e: EntityNotFoundException): ResponseEntity<ApiResponse<Any>> {
        return ResponseEntity(
            ApiResponse(
                code = 404,
                status = HttpStatus.NOT_FOUND,
                message = e.message ?: "Запрашиваемый объект не найден"
            ),
            HttpStatus.NOT_FOUND
        )
    }

    @ExceptionHandler(DataIntegrityViolationException::class)
    fun handleDataIntegrityViolation(e: DataIntegrityViolationException): ResponseEntity<ApiResponse<Any>> {
        return ResponseEntity(
            ApiResponse(
                code = 409,
                status = HttpStatus.CONFLICT,
                message = "Нарушение ограничений целостности данных: ${e.mostSpecificCause.message}"
            ),
            HttpStatus.CONFLICT
        )
    }

}