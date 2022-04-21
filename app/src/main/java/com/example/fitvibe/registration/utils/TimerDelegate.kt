package com.example.fitvibe.registration.utils

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.job
import kotlinx.coroutines.launch

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

        val handler = CoroutineExceptionHandler { _, throwable ->
            exception(throwable)
        }

        job = CoroutineScope(dispatcher + handler).launch {
            while (time != 0) {
                delay(delay)
                onTick(time - 1)
            }
            onFinish()
        }
    }

    override fun cancelTimer() {
        job?.cancel()
    }
}