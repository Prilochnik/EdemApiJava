package com.example.edemapi.exceptions.customExceptions

import java.lang.RuntimeException

class DecodeOptimizationStatusException(errMsg : String) : RuntimeException(errMsg) {
}