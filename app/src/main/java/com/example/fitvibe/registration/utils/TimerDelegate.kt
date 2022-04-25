package com.example.fitvibe.registration.utils

import android.util.Log
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.job
import kotlinx.coroutines.launch
import timber.log.Timber

interface TimerDelegate {

    fun startTimer(
        time: Int,
        delay: Long,
        onTick: (tick: Int) -> Unit,
        onFinish: () -> Unit,
        exception: (e: Throwable) -> Unit
    )

    fun cancelTimer()

}

class TimerDelegateImpl(
    private val dispatcher: CoroutineDispatcher = Dispatchers.Main
) : TimerDelegate {

    private var job: Job? = null

    override fun startTimer(
        time: Int,
        delay: Long,
        onTick: (tick: Int) -> Unit,
        onFinish: () -> Unit,
        exception: (e: Throwable) -> Unit
    ) {
        var timer = time
        val handler = CoroutineExceptionHandler { _, throwable ->
            exception(throwable)
        }

        job = CoroutineScope(dispatcher + handler).launch {
            while (timer != 0) {
                delay(delay)
                onTick(--timer)
            }
            onFinish()
        }
    }

    override fun cancelTimer() {
        job?.cancel()
    }
}