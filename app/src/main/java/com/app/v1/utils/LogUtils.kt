package com.app.v1.utils

import android.util.Log
import com.app.v1.BuildConfig


// Only log VERBOSE if build type is DEBUG
object LogUtils {

    private const val LOG_PREFIX = ""
    private const val LOG_PREFIX_LENGTH = LOG_PREFIX.length
    private const val MAX_LOG_TAG_LENGTH = 23

    fun makeLogTag(str: String): String {
        return if (str.length > MAX_LOG_TAG_LENGTH - LOG_PREFIX_LENGTH) {
            LOG_PREFIX + str.substring(0, MAX_LOG_TAG_LENGTH - LOG_PREFIX_LENGTH - 1)
        } else LOG_PREFIX + str

    }

    fun makeLogTag(cls: Class<*>): String {
        return makeLogTag(cls.simpleName)
    }

    fun v(tag: String, vararg messages: Any) {
        if (BuildConfig.DEBUG) {
            log(tag, Log.VERBOSE, null, *messages)
        }
    }

    fun d(tag: String, vararg messages: Any) {
        // Only log DEBUG if build type is DEBUG
        if (BuildConfig.DEBUG) {
            log(tag, Log.INFO, null, *messages)
        }
    }

    fun i(tag: String, vararg messages: Any) {
        log(tag, Log.INFO, null, *messages)
    }

    fun w(tag: String, vararg messages: Any) {
        log(tag, Log.WARN, null, *messages)
    }

    fun w(tag: String, t: Throwable, vararg messages: Any) {
        log(tag, Log.WARN, t, *messages)
    }

    fun e(tag: String, vararg messages: Any) {
        log(tag, Log.ERROR, null, *messages)
    }

    fun e(tag: String, t: Throwable, vararg messages: Any) {
        log(tag, Log.ERROR, t, *messages)
    }

    fun log(tag: String, level: Int, t: Throwable?, vararg messages: Any) {
        if (Log.isLoggable(tag, level)) {
            val message = if (t == null && messages != null && messages.size == 1) {
                    messages[0].toString()
                } else {
                    val sb = StringBuilder()
                    if (messages != null)
                        for (m in messages) {
                            sb.append(m)
                        }
                    if (t != null) {
                        sb.append('\n').append(Log.getStackTraceString(t))
                    }
                    sb.toString()
                }
            Log.println(level, tag, message)
        }
    }

}