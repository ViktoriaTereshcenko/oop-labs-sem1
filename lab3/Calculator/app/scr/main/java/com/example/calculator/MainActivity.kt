package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import com.example.calculator.databinding.ActivityMainBinding
import net.objecthunter.exp4j.Expression
import net.objecthunter.exp4j.ExpressionBuilder
import java.lang.ArithmeticException

class MainActivity : AppCompatActivity() {
    var lastNumeric = false // Вказує, чи останній введений символ є числом
    var stateError = false // Вказує на наявність помилки у введенні
    var lastDot = false // Вказує, чи останнім введеним символом була крапка
    var resultDisplayed = false // Прапорець, який позначає, чи відображено результат
    var lastResult = "" // Змінна для збереження останнього результату
    private lateinit var expression: Expression
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    // Очистити всі введені дані та скинути стани
    fun onAllClearClick(view: View) {
        binding.dataTv.text = ""
        binding.resultTv.text = ""
        stateError = false
        lastDot = false
        lastNumeric = false
        resultDisplayed = false
        lastResult = ""
        binding.resultTv.visibility = View.GONE
        binding.dataTv.visibility = View.VISIBLE
    }

    // Обчислити вираз і відобразити результат при натисканні на кнопку "="
    fun onEqualClick(view: View) {
        onEqual()
    }

    // Вводити цифри до виразу
    fun onDigitClick(view: View) {
        if (stateError) {
            // Якщо є помилка, замінити вираз новою цифрою
            binding.dataTv.text = (view as Button).text
            stateError = false
        } else {
            // Інакше додати цифру до існуючого виразу
            binding.dataTv.append((view as Button).text)
        }
        lastNumeric = true
        resultDisplayed = false // Скинути прапорець при новому введенні
        binding.resultTv.visibility = View.GONE // Сховати результат при новому введенні
        binding.dataTv.visibility = View.VISIBLE // Зробити видимими введені дані
    }

    // Додати оператор до виразу
    fun onOperatorClick(view: View) {
        if (resultDisplayed) {
            // Якщо результат відображається, використовувати його для нової операції
            binding.dataTv.text = lastResult + (view as Button).text
            resultDisplayed = false
        } else if (!stateError && lastNumeric) {
            binding.dataTv.append((view as Button).text)
        }
        lastDot = false
        lastNumeric = false
        binding.resultTv.visibility = View.GONE // Сховати результат при введенні оператора
        binding.dataTv.visibility = View.VISIBLE // Зробити видимими введені дані
    }

    // Видалити останній символ у виразі
    fun onBackClick(view: View) {
        if (!resultDisplayed) { // Заборонити видалення, якщо результат відображено
            val length = binding.dataTv.text.length
            if (length > 0) {
                binding.dataTv.text = binding.dataTv.text.substring(0, length - 1)
                // Оновити стани
                stateError = false
                lastNumeric = binding.dataTv.text.isNotEmpty() && binding.dataTv.text.last().isDigit()
                lastDot = binding.dataTv.text.contains(".")
            }
            binding.resultTv.visibility = View.GONE // Сховати результат при натисканні Backspace
            binding.dataTv.visibility = View.VISIBLE // Зробити видимими введені дані
        }
    }

    // Очистити поточний вираз
    fun onClearClick(view: View) {
        binding.dataTv.text = ""
        lastNumeric = false
        resultDisplayed = false // Скинути прапорець при натисканні "C"
        binding.resultTv.visibility = View.GONE // Сховати результат
        binding.dataTv.visibility = View.VISIBLE // Зробити видимими введені дані
    }

    // Обчислити поточний вираз і відобразити результат
    fun onEqual() {
        if (lastNumeric && !stateError) {
            val txt = binding.dataTv.text.toString()
            expression = ExpressionBuilder(txt).build()

            try {
                val result = expression.evaluate()
                // Перевірити, чи є результат цілим числом
                if (result == result.toLong().toDouble()) {
                    binding.resultTv.text = "=" + result.toLong().toString()
                    lastResult = result.toLong().toString()
                } else {
                    binding.resultTv.text = "=" + result.toString()
                    lastResult = result.toString()
                }
                binding.resultTv.visibility = View.VISIBLE // Показати результат
                binding.dataTv.visibility = View.GONE // Сховати введені дані
                resultDisplayed = true // Встановити прапорець після відображення результату
            } catch (ex: ArithmeticException) {
                Log.e("evaluate error", ex.toString())
                binding.resultTv.visibility = View.VISIBLE
                binding.resultTv.text = "= Error"
                binding.dataTv.visibility = View.GONE // Сховати введені дані
                stateError = true
                lastNumeric = false
                resultDisplayed = false
            }
        }
    }
}
