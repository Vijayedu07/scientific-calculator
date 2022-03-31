package com.edu.scientificcalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.isVisible
import java.lang.ArithmeticException
import kotlin.math.cos
import kotlin.math.ln
import kotlin.math.sin
import kotlin.math.tan

class MainActivity : AppCompatActivity() {
    private var dpText: TextView? = null
    private var tvResult: TextView? = null
    var lastNumeric: Boolean = false
    var lastdot: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var count = 0
        var llScifiVisibility = findViewById<LinearLayout>(R.id.llScifi)
        var scifiButton  = findViewById<Button>(R.id.scifibutton)
        scifiButton.setOnClickListener {
            count += 1
            llScifiVisibility.isVisible = count%2!=0
        }

    }

    fun onDigit(view: View) {
        dpText = findViewById(R.id.dpView)
        dpText!!.append((view as Button).text.toString())
        lastNumeric = true
    }

    fun onClear(view: View) {
        dpText = findViewById(R.id.dpView)
        tvResult = findViewById(R.id.ansView)
        dpText!!.text = ""
        tvResult!!.text = ""
        lastNumeric = false
        lastdot = false
    }
    fun onOperate(view: View) {
        dpText = findViewById(R.id.dpView)
        if(lastNumeric && !isOperatorAdded(dpText!!.text.toString())){
            dpText!!.append((view as Button).text)
            lastNumeric = false
            lastdot = false
        }
    }
    fun onEquals(view: View) {
        dpText = findViewById(R.id.dpView)
        tvResult = findViewById(R.id.ansView)
        if(lastNumeric){
            var prefix = ""
            var tvValue = dpText!!.text.toString()
            try{
                if(tvValue.startsWith("-")){
                    prefix = "-"
                    tvValue = tvValue.substring(1)
                }

                if(tvValue.contains("-")){
                    val splitValue = tvValue.split("-")
                    var one = splitValue[0]
                    var two = splitValue[1]

                    if(!prefix.isEmpty()){
                        one = prefix + one
                    }

                    tvResult!!.text = (one.toDouble()-two.toDouble()).toString()
                }
                else if(tvValue.contains("+")){
                    val splitValue = tvValue.split("+")
                    var one = splitValue[0]
                    var two = splitValue[1]
                    tvResult!!.text = (one.toDouble()+two.toDouble()).toString()
                }
                else if(tvValue.contains("/")){
                    val splitValue = tvValue.split("/")
                    var one = splitValue[0]
                    var two = splitValue[1]
                    tvResult!!.text = (one.toDouble()/two.toDouble()).toString()
                }
                else if(tvValue.contains("*")){
                    val splitValue = tvValue.split("*")
                    var one = splitValue[0]
                    var two = splitValue[1]
                    tvResult!!.text = (one.toDouble()*two.toDouble()).toString()
                }
                else if(tvValue.contains("SIN")){
                    var splitScifi = dpText!!.text.split("(")
                    var value = splitScifi[1]
                    if(!value.isEmpty()){
                        tvResult!!.text = sin(value.toDouble()).toString()
                    }
                }
                else if(tvValue.contains("COS")){
                    var splitScifi = dpText!!.text.split("(")
                    var value = splitScifi[1]
                    if(!value.isEmpty()){
                        tvResult!!.text = cos(value.toDouble()).toString()
                    }
                }
                else if(tvValue.contains("TAN")){
                    var splitScifi = dpText!!.text.split("(")
                    var value = splitScifi[1]
                    if(!value.isEmpty()){
                        tvResult!!.text = tan(value.toDouble()).toString()
                    }
                }
                else if(tvValue.contains("^")){
                    var splitScifi = dpText!!.text.split("^")
                    var valueOne = splitScifi[0]
                    var valueTwo = splitScifi[1]
                    if(!valueOne.isEmpty() and !valueTwo.isEmpty()){
                        tvResult!!.text = Math.pow(valueOne.toDouble(),valueTwo.toDouble()).toString()
                    }
                }
                else if(tvValue.contains("SQRT")){
                    var splitScifi = dpText!!.text.split("(")
                    var value = splitScifi[1]
                    if(!value.isEmpty()){
                        tvResult!!.text = Math.sqrt(value.toDouble()).toString()
                    }
                }
                else if(tvValue.contains("LOG")){
                    var splitScifi = dpText!!.text.split("(")
                    var value = splitScifi[1]
                    if(!value.isEmpty()){
                        tvResult!!.text = Math.log(value.toDouble()).toString()
                    }
                }
                else if(tvValue.contains("LAN")){
                    var splitScifi = dpText!!.text.split("(")
                    var value = splitScifi[1]
                    if(!value.isEmpty()){
                        tvResult!!.text = ln(value.toDouble()).toString()
                    }
                }
            }catch(e: ArithmeticException){e.printStackTrace()}
        }


    }

    fun onDecimalPoint(view: View){
        if(lastNumeric && !lastdot){
            dpText = findViewById(R.id.dpView)
            dpText!!.append('.'.toString())
            lastNumeric = false
            lastdot = true
        }

    }


    private fun isOperatorAdded(value: String): Boolean{
        return if(value.startsWith("-")){
            false
        }else{
            (value.contains("/") || value.contains("*") || value.contains("+") || value.contains("-"))
        }
    }

    fun onBackSpace(view: View) {

        dpText = findViewById(R.id.dpView)
        if(!dpText!!.text.toString().isEmpty()){
            dpText!!.text = dpText!!.text.substring(0,dpText!!.text.length-1)
        }
    }

    fun onscifi(view: View) {
        dpText = findViewById(R.id.dpView)
        var prefixValue: Boolean = true
        if((view as Button).text == "^"){
//            var powerSymbol = (view as Button).text.toString()
//            var value = powerSymbol.split('^')
//            var valueOne = value[0]
//            var valueTwo = value[1]
//            if(!valueOne.isEmpty()){
//                prefixValue = true
//                if(prefixValue)
//            }

            dpText!!.append((view as Button).text.toString())
        }
        else{
            dpText!!.append((view as Button).text.toString()+"(")
        }
        tvResult = findViewById(R.id.ansView)
        var tvValue = dpText!!.text.toString()
//            if(tvValue.contains("SIN")){
//                var splitScifi = dpText!!.text.split("(")
//                var value = splitScifi[1]
//                if(!value.isEmpty()){
//                    tvResult!!.text = sin(value.toDouble()).toString()
//                }
//            }
    }
}