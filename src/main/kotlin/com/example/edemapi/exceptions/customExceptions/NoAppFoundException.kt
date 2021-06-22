package com.example.edemapi.exceptions.customExceptions

import java.lang.RuntimeException

class NoAppFoundException(errMsg : String) : RuntimeException(errMsg) {
}