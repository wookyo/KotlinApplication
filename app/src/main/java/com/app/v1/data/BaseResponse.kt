package com.app.v1.data

class BaseResponse<T> {
    var result: T? = null
    var resultcode: Int = -1
    var resultmessage: String? = null
    var error: Throwable? = null

    constructor(result: T?) {
        this.result = result
        this.resultcode = 200
        this.resultmessage = null
    }

    constructor(error: Throwable?) {
        this.result = null
        this.error = error
        this.resultcode = -1
        this.resultmessage = null
    }

    constructor(code: Int, message: String) {
        this.result = null
        this.error = null
        this.resultcode = code
        this.resultmessage = message
    }
}