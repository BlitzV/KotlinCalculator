package com.app.kotlin_calculator

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    // Instance Variables
    private var currentNumber: String? = null
    private var currentOperator: OPERATOR? = null
    private var stringNumberAtLeft: String? = null
    private var stringNumberAtRight: String? = null
    private var calculationResult: Double = 0.0
    private var calculationsString: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        edtNumber.isEnabled = false
        edtNumber.inputType = InputType.TYPE_NULL

        currentNumber = ""
        calculationsString = ""
    }

    fun buttonTapped(view: View) {

        when (view.id) {

            R.id.btn0 -> numberIsTapped(0)
            R.id.btn1 -> numberIsTapped(1)
            R.id.btn2 -> numberIsTapped(2)
            R.id.btn3 -> numberIsTapped(3)
            R.id.btn4 -> numberIsTapped(4)
            R.id.btn5 -> numberIsTapped(5)
            R.id.btn6 -> numberIsTapped(6)
            R.id.btn7 -> numberIsTapped(7)
            R.id.btn8 -> numberIsTapped(8)
            R.id.btn9 -> numberIsTapped(9)

            R.id.btnDivide -> {

                operatorIsTapped(OPERATOR.DIVIDE)
                calculationsString += " / "

            }
            R.id.btnMultiply -> {
                operatorIsTapped(OPERATOR.MULTIPLY)
                calculationsString += " * "
            }

            R.id.btnMinus -> {
                operatorIsTapped(OPERATOR.SUBTRACT)
                calculationsString += " - "

            }

            R.id.btnPlus -> {

                operatorIsTapped(OPERATOR.PLUS)
                calculationsString += " + "
            }

            R.id.btnEqual -> operatorIsTapped(OPERATOR.EQUAL)

            R.id.btnPercent -> implementPercent()
            R.id.btnClear -> clear()

        }

        txtCalculations.setText(calculationsString)


    }

    private fun numberIsTapped(i: Int) {
        // currentNumber = currentNumber + tappedNumber.toString()
        currentNumber += i.toString()
        edtNumber.setText(currentNumber)
        calculationsString = currentNumber
        txtCalculations.text = calculationsString
    }

    private fun operatorIsTapped(tappedOperator: OPERATOR){
        if (currentOperator != null) {

            if (currentNumber != "") {


                stringNumberAtRight = currentNumber
                currentNumber = ""

                when (currentOperator) {

                    OPERATOR.PLUS -> calculationResult = stringNumberAtLeft!!.toDouble() +
                            stringNumberAtRight!!.toDouble()
                    OPERATOR.SUBTRACT -> calculationResult = stringNumberAtLeft!!.toDouble() -
                            stringNumberAtRight!!.toDouble()
                    OPERATOR.MULTIPLY -> calculationResult = stringNumberAtLeft!!.toDouble() *
                            stringNumberAtRight!!.toDouble()
                    OPERATOR.DIVIDE -> calculationResult = stringNumberAtLeft!!.toDouble() /
                            stringNumberAtRight!!.toDouble()

                    else -> Toast.makeText(this, "select operator", Toast.LENGTH_SHORT ).show()
                }

                stringNumberAtLeft = calculationResult.toString()
                edtNumber.setText(calculationResult.toString())
                calculationsString = stringNumberAtLeft

            }
        } else {

            stringNumberAtLeft = currentNumber
            currentNumber = ""

        }

        currentOperator = tappedOperator
    }

    private fun implementPercent(){
        val percentValue = edtNumber.text.toString().toDouble() / 100
        currentNumber = percentValue.toString()
        edtNumber.setText(currentNumber)
    }

    private fun clear(){
        stringNumberAtLeft = ""
        stringNumberAtRight = ""
        calculationResult = 0.0
        currentNumber = ""
        currentOperator = null
        edtNumber.setText("0")
        calculationsString = "0"
    }

    private enum class OPERATOR {
        PLUS, SUBTRACT, MULTIPLY, DIVIDE, EQUAL
    }

}


