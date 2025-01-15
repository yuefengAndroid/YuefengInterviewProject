package com.example.yuefenginterviewproject

import android.text.TextUtils
import android.util.Log
import org.json.JSONArray
import org.json.JSONObject
import java.util.Date

object MyLog {
    /**
     * Tag統一變成EMyLog
     */
    const val MAHORO_TAG_MODE_OPEN = true

    /**
     * Tag自己輸入
     */
    const val MAHORO_TAG_MODE_CLOSE = false

    /**
     * 讓Log變得很漂亮，小心不要烤了他
     */
    const val MESSAGE_BEAUTIFUL_MODE_OPEN = true

    /**
     * 讓Log正常顯示
     */
    const val MESSAGE_BEAUTIFUL_MODE_CLOSE = false

    /**
     * 關閉全部的Log
     */
    const val LOG_OFF = 6

    /**
     * v等級以上的Log，打開這個的話能看到全部的API發送和接收訊息
     */
    const val VERBOSE = 1

    /**
     * d等級以上的Log
     */
    const val DEBUG = 2

    /**
     * w等級以上的Log
     */
    const val WARN = 3

    /**
     * i等級以上的Log
     */
    const val INFO = 4

    /**
     * e等級以上的Log
     */
    const val ERROR = 5
    private const val LOG_HEADER = "************************************************************************"
    private const val LOG_THREAD = "執行緒 : "
    private const val LOG_FUNCTION = "執行方法(類名:行數) : "
    private const val LOG_HORIZONTAL = "================================================================="
    private const val LOG_MESSAGE_WALL = ""
    private const val LOG_FOOTER = "************************************************************************"
    private const val LOG_LEFT_PARENTHESES = "("
    private const val LOG_RIGHT_PARENTHESES = ")"
    private const val LOG_LEFT_SQUARE_BRACKET = "["
    private const val LOG_RIGHT_SQUARE_BRACKET = "]"
    private const val LOG_LEFT_CURLY_BRACKET = "{"
    private const val LOG_RIGHT_CURLY_BRACKET = "}"
    private const val LOG_COLON = ":"
    private const val LOG_POST = "|*Post字串無縮排 : \n"
    private const val LOG_POST_NORMAL = "\n Post字串 : "
    private const val LOG_POST_INDENTATION = "|*Post字串有縮排 : \n"
    private var mahoroTegText = "log"
    private var level = LOG_OFF
    private var mahoroTagMode = MAHORO_TAG_MODE_CLOSE
    private var messageBeautifulMode = MESSAGE_BEAUTIFUL_MODE_OPEN

    /**
     * 設定Tag的自定義文字，預設"maho日期"
     */
    fun setMahoroTegText(mahoroTegText: String) {
        MyLog.mahoroTegText = mahoroTegText
    }

    /**
     * 2
     * 設定Tag是否加上自定義文字，預設開啟
     */
    fun isMahoroTagModeOpen(open: Boolean) {
        mahoroTagMode = open
    }

    /**
     * 設定訊息漂亮模式，預設開啟
     */
    fun isMessageBeautifulModeOpen(open: Boolean) {
        messageBeautifulMode = open
    }

    /**
     * 傳入Log顯示等級，預設全關
     */
    @JvmStatic
    fun setLogLevel(level: Int) {
        MyLog.level = level
    }

    /**
     * 判斷能不能顯示Log
     */
    private fun isLogCanShow(logLevel: Int): Boolean {
        return level > logLevel
    }

    private fun logBuild(level: Int, tag: String, message: String) {
        var tag = tag
        if (isLogCanShow(level)) {
            return
        }
        if (MAHORO_TAG_MODE_OPEN || TextUtils.isEmpty(tag)) {
            tag = mahoroTegText + today
        }
        if (messageBeautifulMode) {
            logShow(level, tag, LOG_HEADER)
            //    logShow(level, tag, LOG_THREAD + Thread.currentThread().name)
            logShow(level, tag, LOG_FUNCTION + stackTraceElement!!.methodName + LOG_LEFT_PARENTHESES + stackTraceElement!!.fileName + LOG_COLON + stackTraceElement!!.lineNumber + LOG_RIGHT_PARENTHESES)
            logShow(level, tag, LOG_HORIZONTAL)
            logShow(level, tag, LOG_MESSAGE_WALL + message)
            //    logShow(level, tag, LOG_FOOTER)
        } else {
            logShow(level, tag, message)
        }
    }

    /**
     * 讓Log的Tag自動加上月、日
     */
    private val today: String
        private get() {
            val date = Date()
            val month = String.format("%tm", date)
            val day = String.format("%td", date)
            return month + day
        }

    /**
     * 抓Log的相關資料
     */
    private val stackTraceElement: StackTraceElement?
        private get() {
            var stackTraceElement: StackTraceElement? = null
            val stackTraceElements = Thread.currentThread().stackTrace
            var target = false
            for (traceElement in stackTraceElements) {
                val isfindAWDLogClass = traceElement.className == MyLog::class.java.name
                if (target && !isfindAWDLogClass) {
                    stackTraceElement = traceElement
                    break
                }
                target = isfindAWDLogClass
            }
            return stackTraceElement
        }

    private fun logShow(level: Int, tag: String, message: String) {
        when (level) {
            VERBOSE -> Log.v(tag, message)
            DEBUG -> Log.d(tag, message)
            WARN -> Log.w(tag, message)
            INFO -> Log.i(tag, message)
            ERROR -> Log.e(tag, message)
            else -> Log.v(tag, message)
        }
    }

    /**
     * Log級別 VERBOSE
     */
    fun v(tag: String, message: String) {
        logBuild(VERBOSE, tag, message)
    }

    fun v(message: String) {
        logBuild(VERBOSE, "", message)
    }

    /**
     * Log級別 DEBUG
     */
    fun d(tag: String, message: String) {
        logBuild(DEBUG, tag, message)
    }

    @JvmStatic
    fun d(message: String) {
        logBuild(DEBUG, "", message)
    }

    /**
     * Log級別 WARN
     */
    fun w(tag: String, message: String) {
        logBuild(WARN, tag, message)
    }

    fun w(message: String) {
        logBuild(WARN, "", message)
    }

    /**
     * Log級別 INFO
     */
    fun i(tag: String, message: String) {
        logBuild(INFO, tag, message)
    }

    fun i(message: String) {
        logBuild(INFO, "", message)
    }

    /**
     * Log級別 ERROR
     */
    fun e(tag: String, message: String) {
        logBuild(ERROR, tag, message)
    }

    fun e(message: String) {
        logBuild(ERROR, "", message)
    }

    /**
     * Log級別 VERBOSE，印出JSON字串用
     */
    fun api(apiInfo: String, apiJson: String) {
        if (isLogCanShow(VERBOSE)) {
            return
        }
        if (messageBeautifulMode && TextUtils.isEmpty(apiJson)) {
            logShow(VERBOSE, mahoroTegText + today, LOG_HEADER)
            logShow(VERBOSE, mahoroTegText + today, LOG_MESSAGE_WALL + apiInfo)
            logShow(VERBOSE, mahoroTegText + today, LOG_FOOTER)
            return
        }
        if (messageBeautifulMode && !TextUtils.isEmpty(apiJson)) {
            try {
                if (apiJson.startsWith(LOG_LEFT_CURLY_BRACKET)) {
                    val joMsg = JSONObject(apiJson)
                    logShow(VERBOSE, mahoroTegText + today, LOG_HEADER)
                    logShow(VERBOSE, mahoroTegText + today, LOG_MESSAGE_WALL + apiInfo)
                    logShow(VERBOSE, mahoroTegText + today, LOG_HORIZONTAL)
                    logShow(VERBOSE, mahoroTegText + today, LOG_POST + apiJson)
                    logShow(VERBOSE, mahoroTegText + today, LOG_POST_INDENTATION + joMsg.toString(4))
                    logShow(VERBOSE, mahoroTegText + today, LOG_FOOTER)
                }
                if (apiJson.startsWith(LOG_LEFT_SQUARE_BRACKET)) {
                    val jaMsg = JSONArray(apiJson)
                    logShow(VERBOSE, mahoroTegText + today, LOG_HEADER)
                    logShow(VERBOSE, mahoroTegText + today, LOG_MESSAGE_WALL + apiInfo)
                    logShow(VERBOSE, mahoroTegText + today, LOG_HORIZONTAL)
                    logShow(VERBOSE, mahoroTegText + today, LOG_POST + apiJson)
                    logShow(VERBOSE, mahoroTegText + today, LOG_POST_INDENTATION + jaMsg.toString(4))
                    logShow(VERBOSE, mahoroTegText + today, LOG_FOOTER)
                }
            } catch (e: Exception) {
                logShow(VERBOSE, mahoroTegText + today, LOG_HEADER)
                logShow(VERBOSE, mahoroTegText + today, LOG_MESSAGE_WALL + apiInfo)
                logShow(VERBOSE, mahoroTegText + today, LOG_HORIZONTAL)
                logShow(VERBOSE, mahoroTegText + today, LOG_POST + apiJson)
                logShow(VERBOSE, mahoroTegText + today, LOG_FOOTER)
            }
            return
        }
        if (messageBeautifulMode == MESSAGE_BEAUTIFUL_MODE_CLOSE) {
            logShow(VERBOSE, mahoroTegText + today, apiInfo + LOG_POST_NORMAL + apiJson)
            return
        }
    }
}