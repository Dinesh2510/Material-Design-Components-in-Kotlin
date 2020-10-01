package com.example.kotlindemoapp.Activities

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlindemoapp.R


class CalclulatorActivity : AppCompatActivity() {
    private var zero: Button? = null
    private var one: Button? = null
    private var two: Button? = null
    private var three: Button? = null
    private var four: Button? = null
    private var five: Button? = null
    private var six: Button? = null
    private var seven: Button? = null
    private var eight: Button? = null
    private var nine: Button? = null
    private var add: Button? = null
    private var sub: Button? = null
    private var mul: Button? = null
    private var div: Button? = null
    private var equal: Button? = null
    private var clear: Button? = null
    private var info: TextView? = null
    private var result: TextView? = null
    private val ADDITION = '+'
    private val SUBTRACTION = '-'
    private val MULTIPLICATION = '*'
    private val DIVISION = '/'
    private val EQU = 0.toChar()
    private var val1 = Double.NaN
    private var val2 = 0.0
    private var ACTION = 0.toChar()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calclulator)
        setupUIViews()

        zero!!.setOnClickListener { info!!.text = info!!.text.toString() + "0" }

        one!!.setOnClickListener { info!!.text = info!!.text.toString() + "1" }

        two!!.setOnClickListener { info!!.text = info!!.text.toString() + "2" }

        three!!.setOnClickListener { info!!.text = info!!.text.toString() + "3" }

        four!!.setOnClickListener { info!!.text = info!!.text.toString() + "4" }

        five!!.setOnClickListener { info!!.text = info!!.text.toString() + "5" }

        six!!.setOnClickListener { info!!.text = info!!.text.toString() + "6" }

        seven!!.setOnClickListener { info!!.text = info!!.text.toString() + "7" }

        eight!!.setOnClickListener { info!!.text = info!!.text.toString() + "8" }

        nine!!.setOnClickListener { info!!.text = info!!.text.toString() + "9" }

        sub!!.setOnClickListener {
            compute()
            ACTION = SUBTRACTION
            result!!.text = "$val1-"
            info?.setText(" ")
        }

        add!!.setOnClickListener {
            compute()
            ACTION = ADDITION
            result!!.text = "$val1+"
            info?.setText("")
        }

        mul!!.setOnClickListener {
            compute()
            ACTION = MULTIPLICATION
            result!!.text = "$val1*"
            info?.setText("")
        }

        div!!.setOnClickListener {
            compute()
            ACTION = DIVISION
            result!!.text = "$val1/"
            info?.setText("")
        }

        equal!!.setOnClickListener {
            compute()
            ACTION = EQU
            result!!.text = result!!.text.toString() + val2.toString() + "= " + val1.toString()
            // 5 + 5 = 10
            info?.setText("")
        }

        clear!!.setOnClickListener {
            if (info!!.text.length > 0) {
                val name: CharSequence = info!!.text.toString()
                info!!.text = name.subSequence(0, name.length - 1)
            } else {
                val1 = Double.NaN
                val2 = Double.NaN
                info!!.text = null
                result?.setText("")
            }
        }
    }

    private fun setupUIViews() {
        one = findViewById<Button>(R.id.btn1)
        zero = findViewById<Button>(R.id.btn0)
        two = findViewById<Button>(R.id.btn2)
        three = findViewById<Button>(R.id.btn3)
        four = findViewById<Button>(R.id.btn4)
        five = findViewById<Button>(R.id.btn5)
        six = findViewById<Button>(R.id.btn6)
        seven = findViewById<Button>(R.id.btn7)
        eight = findViewById<Button>(R.id.btn8)
        nine = findViewById<Button>(R.id.btn9)
        add = findViewById<Button>(R.id.btnadd)
        sub = findViewById<Button>(R.id.btnsub)
        mul = findViewById<Button>(R.id.btnmul)
        div = findViewById<Button>(R.id.btndivide)
        equal = findViewById<Button>(R.id.btnequal)
        info = findViewById<TextView>(R.id.tvControl)
        result = findViewById<TextView>(R.id.tvResult)
        clear = findViewById<Button>(R.id.btnclear)
    }

    private fun compute() {
        if (!java.lang.Double.isNaN(val1)) {
            val2 = info!!.text.toString().toDouble()
            when (ACTION) {
                ADDITION -> val1 = val1 + val2
                SUBTRACTION -> val1 = val1 - val2
                MULTIPLICATION -> val1 = val1 * val2
                DIVISION -> val1 = val1 / val2
                EQU -> {
                }
            }
        } else {
            val1 = info!!.text.toString().toDouble()
        }
    }
}