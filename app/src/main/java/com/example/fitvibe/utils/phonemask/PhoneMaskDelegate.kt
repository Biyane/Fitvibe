package com.example.fitvibe.utils.phonemask

import android.widget.EditText
import ru.tinkoff.decoro.MaskImpl
import ru.tinkoff.decoro.parser.UnderscoreDigitSlotsParser
import ru.tinkoff.decoro.watchers.MaskFormatWatcher

internal const val PHONE_MASK = "+7 (___) ___ __ __"

interface PhoneMaskDelegate {
    fun setPhoneMask(phoneEditText: EditText)
}

class PhoneMaskDelegateImpl: PhoneMaskDelegate {

    private val digitSlots = UnderscoreDigitSlotsParser().parseSlots(PHONE_MASK)

    private lateinit var phoneFormatWatcher: MaskFormatWatcher

    override fun setPhoneMask(phoneEditText: EditText) {
        digitSlots[1].setValueInterpreter {
            when (it) {
                '8' -> '7'
                else -> it
            }
        }
        val phoneMask: MaskImpl = MaskImpl.createTerminated(digitSlots)
        phoneFormatWatcher = MaskFormatWatcher(phoneMask)
        phoneEditText.maxLines = PHONE_MASK.length
        phoneFormatWatcher.installOn(phoneEditText)
    }
}