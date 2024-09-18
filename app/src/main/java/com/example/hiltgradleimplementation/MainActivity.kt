package com.example.hiltgradleimplementation

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.example.hiltgradleimplementation.counterViewModel.ViewModelCounter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var tvCounter: TextView
    private val counterViewModel: ViewModelCounter by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvCounter = findViewById(R.id.tvCounter)


        lifecycleScope.launch {
            counterViewModel.counterValue.collect { count ->
                tvCounter.text = count.toString()
            }
        }

        counterViewModel.incrementCounter()
    }
}