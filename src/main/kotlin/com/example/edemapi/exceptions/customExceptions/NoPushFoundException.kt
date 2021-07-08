package com.example.edemapi.exceptions.customExceptions

import java.lang.RuntimeException

class NoPushFoundException(errMsg : String) : RuntimeException(errMsg) {
}