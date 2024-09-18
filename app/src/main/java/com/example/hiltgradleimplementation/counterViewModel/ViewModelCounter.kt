package com.example.hiltgradleimplementation.counterViewModel

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class ViewModelCounter @Inject constructor(
    private val counterSharedPreferences: SharedPreferences
): ViewModel(){

    companion object {
        private const val COUNTER_KEY = "counter_value"
        private const val DEFAULT_COUNT = 0
    }

    private val counterApplication = MutableStateFlow(counterSharedPreferences.getInt(COUNTER_KEY, DEFAULT_COUNT))
    val counterValue: StateFlow<Int> = counterApplication.asStateFlow()

    fun incrementCounter() {
        val newCount = counterApplication.value + 1
        counterApplication.value = newCount
        saveCounterToPreferences(newCount)
    }

    private fun saveCounterToPreferences(count: Int) {
        with(counterSharedPreferences.edit()) {
            putInt(COUNTER_KEY, count)
            apply()
        }
    }
    }
