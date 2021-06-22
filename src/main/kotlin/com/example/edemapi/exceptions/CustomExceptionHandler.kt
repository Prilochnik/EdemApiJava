package com.example.edemapi.exceptions

import com.example.edemapi.exceptions.customExceptions.NoAppFoundException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@ControllerAdvice
class CustomExceptionHandler : ResponseEntityExceptionHandler() {

    @ExceptionHandler(NoAppFoundException::class)
    fun handleNoAppFoundException(ex : NoAppFoundException) : ResponseEntity<Any> {
        val errorMsg = "Error while try to find app by package"
        val err = ApiError(HttpStatus.BAD_REQUEST, ex.localizedMessage, errorMsg)
        return ResponseEntity(err, err.status)
    }


}