package com.example.androidapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.androidapp.ui.theme.AndroidAppTheme
import java.time.LocalDate

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {}
    }

    private fun calculateDate(number: Long): LocalDate {
        val daysToAdd = (number / 100_000)
        val startDate = LocalDate.of(1899, 12, 31)

        return startDate.plusDays(daysToAdd)
    }

    private fun calculateSex(number: Long): String {
        val digit = number.toString()[8].toInt()

        return if (digit % 2 == 1) {
            "чоловік"
        } else {
            "жінка"
        }
    }
}