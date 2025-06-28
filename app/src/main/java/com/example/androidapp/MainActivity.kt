package com.example.androidapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androidapp.ui.theme.AndroidAppTheme
import java.time.LocalDate

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    InputOutputScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
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

    @Composable
    fun InputOutputScreen(modifier: Modifier = Modifier) {
        var inputNumber by remember { mutableStateOf("") }
        var birthYearResult by remember { mutableStateOf("Рік народження: ") }
        var sexResult by remember { mutableStateOf("Стать: ") }

        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            TextField(
                value = inputNumber,
                onValueChange = { newValue ->
                    val filteredValue = newValue.filter { it.isDigit() }
                    if (filteredValue.length <= 10) {
                        inputNumber = filteredValue
                        if (filteredValue.length == 10) {
                            try {
                                val numberAsLong = filteredValue.toLong()
                                val date = MainActivity().calculateDate(numberAsLong)
                                birthYearResult = "Рік народження: ${date}"
                                val sex = MainActivity().calculateSex(numberAsLong)
                                sexResult = "Стать: $sex"
                            } catch (e: IllegalArgumentException) {
                                birthYearResult = "Помилка: ${e.message}"
                                sexResult = "Стать: -"
                            } catch (e: Exception) {
                                birthYearResult = "Помилка: ${e.localizedMessage}"
                                sexResult = "Стать: -"
                            }
                        } else {
                            birthYearResult = "Рік народження: "
                            sexResult = "Стать: "
                        }
                    }
                },
                label = { Text("Введіть 10-значне число") },
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
            Spacer(modifier = Modifier.height(30.dp))
            Text(text = birthYearResult)
            Spacer(modifier = Modifier.height(15.dp))
            Text(text = sexResult)
        }
    }
}