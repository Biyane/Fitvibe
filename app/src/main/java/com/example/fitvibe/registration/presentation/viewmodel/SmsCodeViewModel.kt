package com.example.fitvibe.registration.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.fitvibe.registration.utils.TimerDelegate
import com.example.fitvibe.registration.utils.TimerDelegateImpl

class SmsCodeViewModel : ViewModel(), TimerDelegate by TimerDelegateImpl() {

    private var _tickLiveData: MutableLiveData<Int> = MutableLiveData(10)
    val tickLiveData get() = _tickLiveData

    private var _tickStringLiveData: MutableLiveData<String> = MutableLiveData()
    val tickStringLiveData get() = _tickStringLiveData

    private var _throwableLiveData: MutableLiveData<Throwable> = MutableLiveData()
    val throwableLiveData get() = _throwableLiveData

    init {
        startTimer(
            time = _tickLiveData.value!!,
            delay = ONE_SECONDS_IN_MILLIS,
            onTick = { tick ->
                handleTick(tick)
            },
            onFinish = {
                handleSecondChance()
            },
            exception = { exception ->
                _throwableLiveData.value = exception
            }
        )
    }

    private fun handleSecondChance() {

    }

    private fun handleTick(tick: Int) {
        _tickLiveData.value = tick
        updateTickString(tick)
    }

    private fun updateTickString(tick: Int) {
        _tickStringLiveData.value = when {
            tick > 70 -> "0${1}:${tick - 60}"
            tick > 60 -> "0${1}:0${tick - 60}"
            tick > 10 -> "00:${tick}"
            else -> "00:0${tick}"
        }
    }

    companion object {
        private const val ONE_SECONDS_IN_MILLIS = 1000L
    }


}