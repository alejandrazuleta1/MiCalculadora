package com.alejandrazuleta.calculadora

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.editText.setShowSoftInputOnFocus(false)

        imageView.setOnClickListener{
            if(editText.text.toString().length>0){
                editText.text=editText.text.toString().substring(0,editText.text.toString().length-1).toEditable()
                editText.setSelection(editText.text.length)
            }
        }

        var num1=0.0
        var num2=0.0
        var resultado=0.0
        var aux=0
        var index=0

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
            if(editText.text.toString().length>0) {
                num1 = editText.text.toString().toDouble()
                editText.append("+")
                aux = 1
            }
        }
        bmenos.setOnClickListener {
            if(editText.text.toString().length>0) {
                num1 = editText.text.toString().toDouble()
                editText.append("-")
                aux = 2
            }
        }
        bpor.setOnClickListener {
            if(editText.text.toString().length>0) {
                num1 = editText.text.toString().toDouble()
                editText.append("*")
                aux = 3
            }
        }
        bdiv.setOnClickListener {
            if(editText.text.toString().length>0) {
                num1 = editText.text.toString().toDouble()
                editText.append("/")
                aux = 4
            }
        }

        bigual.setOnClickListener {
            if(editText.text.toString().length>0) {
                when (aux) {
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
                }
            }
        }





    }

    fun String.toEditable(): Editable = Editable.Factory.getInstance().newEditable(this)
}
