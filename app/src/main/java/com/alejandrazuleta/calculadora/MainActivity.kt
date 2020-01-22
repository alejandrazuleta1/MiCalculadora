package com.alejandrazuleta.calculadora

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.math.pow


class MainActivity : AppCompatActivity() {

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.editText.setShowSoftInputOnFocus(false)

        imageView.setOnClickListener {
            if (editText.text.toString().length > 0) {
                editText.text =
                    editText.text.toString().substring(0, editText.text.toString().length - 1)
                        .toEditable()
                editText.setSelection(editText.text.length)
            }
        }

        var num1 = 0.0
        var num2 = 0.0
        var aux = 0
        var index = 0

        b0.setOnClickListener {
            editText.append("0")
        }
        b1.setOnClickListener {
            editText.append("1")
        }
        b2.setOnClickListener {
            editText.append("2")
        }
        b3.setOnClickListener {
            editText.append("3")
        }
        b4.setOnClickListener {
            editText.append("4")
        }
        b5.setOnClickListener {
            editText.append("5")
        }
        b6.setOnClickListener {
            editText.append("6")
        }
        b7.setOnClickListener {
            editText.append("7")
        }
        b8.setOnClickListener {
            editText.append("8")
        }
        b9.setOnClickListener {
            editText.append("9")
        }

        bpunto.setOnClickListener {
            editText.append(".")
        }

        bmas.setOnClickListener {
            if (editText.text.toString().length > 0) {
                //num1 = editText.text.toString().toDouble()
                editText.append("+")
                aux = 1
            }
        }
        bmenos.setOnClickListener {
            if (editText.text.toString().length > 0) {
                //num1 = editText.text.toString().toDouble()
                editText.append("-")
                aux = 2
            }
        }
        bpor.setOnClickListener {
            if (editText.text.toString().length > 0) {
                //num1 = editText.text.toString().toDouble()
                editText.append("*")
                aux = 3
            }
        }
        bdiv.setOnClickListener {
            if (editText.text.toString().length > 0) {
                //num1 = editText.text.toString().toDouble()
                editText.append("/")
                aux = 4
            }
        }

        bigual.setOnClickListener {
            if (editText.text.toString().length > 0) {
                val resultado = evaluate(editText.text.toString())
                editText.text = resultado.toString().toEditable()
                editText.setSelection(editText.text.length)

                /*when (aux) {
                    1 -> {
                        index = editText.text.toString().indexOf("+")
                        num2 = editText.text.toString().substring(index + 1).toDouble()
                        resultado = num1 + num2
                        editText.text = resultado.toString().toEditable()
                        editText.setSelection(editText.text.length)
                    }
                    2 -> {
                        index = editText.text.toString().indexOf("-")
                        num2 = editText.text.toString().substring(index + 1).toDouble()
                        resultado = num1 - num2
                        editText.text = resultado.toString().toEditable()
                        editText.setSelection(editText.text.length)
                    }
                    3 -> {
                        index = editText.text.toString().indexOf("*")
                        num2 = editText.text.toString().substring(index + 1).toDouble()
                        resultado = num1 * num2
                        editText.text = resultado.toString().toEditable()
                        editText.setSelection(editText.text.length)
                    }
                    else -> {
                        index = editText.text.toString().indexOf("/")
                        num2 = editText.text.toString().substring(index + 1).toDouble()
                        resultado = num1 / num2
                        editText.text = resultado.toString().toEditable()
                        editText.setSelection(editText.text.length)
                    }
                }*/
            }
        }
    }

    private enum class Operators(val sign: Char) {
        MINUS('-'),
        PLUS('+'),
        MULTIPLY('*'),
        DIVISION('/'),
    }

    private fun evaluate(expression: String): Double {
        for (operator in Operators.values()) {
            var position = expression.reversed().lastIndexOf(operator.sign)
            if(position>0 && position<expression.length){
                val partialExpressions = expression.split(position)
                val left = partialExpressions[0]
                val right = partialExpressions[1]

                val value0 = evaluate(left)
                val value1 = evaluate(right)

                val res = when (operator) {
                    Operators.PLUS -> value0 + value1
                    Operators.MINUS -> value0 - value1
                    Operators.DIVISION -> {
                        if (value1 == 0.0)
                            throw ArithmeticException("Divide By Zero")
                        value0 / value1
                    }
                    Operators.MULTIPLY -> value0 * value1
                }
                return res
            }
        }
        /*
            there are no more operators left in the expression, and the
            expression is not solved yet so the expression must be a value
            return the value
         */
        return expression.toDouble()
    }

    // lastIndexOf()
    private fun String.lastIndexOf(char:Char):Int {
        val i = this.indexOf(char)
        return this.length-i-1
    }

    // String.split()
    private fun String.split(position: Int) =
        listOf(
            this.substring(0, position),
            this.substring(position + 1, this.length)
        )


    fun String.toEditable(): Editable = Editable.Factory.getInstance().newEditable(this)
}
