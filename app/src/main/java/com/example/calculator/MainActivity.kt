package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {

    lateinit var etShowNumber: EditText // Deklarasikan secara manual
    var isNewOp = true
    var dot = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inisialisasi EditText menggunakan findViewById
        etShowNumber = findViewById(R.id.etShowNumber)
    }

    fun buNumberEvent(view: View) {
        if (isNewOp) {
            etShowNumber.setText("")
        }
        isNewOp = false
        val buSelect = view as Button
        var buClickValue: String = etShowNumber.text.toString()

        when (buSelect.id) {
            R.id.bu0 -> buClickValue += "0"
            R.id.bu1 -> buClickValue += "1"
            R.id.bu2 -> buClickValue += "2"
            R.id.bu3 -> buClickValue += "3"
            R.id.bu4 -> buClickValue += "4"
            R.id.bu5 -> buClickValue += "5"
            R.id.bu6 -> buClickValue += "6"
            R.id.bu7 -> buClickValue += "7"
            R.id.bu8 -> buClickValue += "8"
            R.id.bu9 -> buClickValue += "9"
            R.id.buDot -> if (!dot) {
                buClickValue += "."
                dot = true
            }
            R.id.buPlusMinus -> buClickValue = if (buClickValue.startsWith("-")) {
                buClickValue.substring(1)
            } else {
                "-$buClickValue"
            }
        }
        etShowNumber.setText(buClickValue)
    }

    var op = "X"
    var oldNumber = ""

    fun buOpEvent(view: View) {
        val buSelect = view as Button
        when (buSelect.id) {
            R.id.buMul -> op = "X"
            R.id.buDiv -> op = "÷"
            R.id.buSub -> op = "-"
            R.id.buSum -> op = "+"
        }
        oldNumber = etShowNumber.text.toString()
        isNewOp = true
        dot = false
    }

    fun buEqualEvent(view: View) {
        val newNumber = etShowNumber.text.toString()
        var finalNumber: Double? = null
        when (op) {
            "X" -> finalNumber = oldNumber.toDouble() * newNumber.toDouble()
            "÷" -> {
                if (newNumber == "0") {
                    etShowNumber.setText("Error")
                    return
                }
                finalNumber = oldNumber.toDouble() / newNumber.toDouble()
            }
            "-" -> finalNumber = oldNumber.toDouble() - newNumber.toDouble()
            "+" -> finalNumber = oldNumber.toDouble() + newNumber.toDouble()
        }
        etShowNumber.setText(finalNumber.toString())
        isNewOp = true
    }

    fun buPercentEvent(view: View) {
        val number = (etShowNumber.text.toString().toDouble()) / 100
        etShowNumber.setText(number.toString())
        isNewOp = true
    }

    fun buCleanEvent(view: View) {
        etShowNumber.setText("")
        isNewOp = true
        dot = false
    }
}
