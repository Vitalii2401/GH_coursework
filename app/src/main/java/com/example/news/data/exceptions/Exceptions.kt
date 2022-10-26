package com.example.news.data.exceptions

open class AppExceptions : RuntimeException {
    constructor()
    constructor(message: String) : super(message)
}

class ConnectionException(
    message: String
) : AppExceptions(message)

class BackendException() : AppExceptions()