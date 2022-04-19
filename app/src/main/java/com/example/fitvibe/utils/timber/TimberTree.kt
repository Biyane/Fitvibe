package com.example.fitvibe.utils.timber

import timber.log.Timber

class TimberTree : Timber.DebugTree() {

    override fun createStackElementTag(element: StackTraceElement): String? {
        return ("${super.createStackElementTag(element)}(${element.fileName}:${element.lineNumber})")
    }
}