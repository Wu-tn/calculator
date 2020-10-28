package com.example.calculator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(),View.OnClickListener {
    private var cal = calculate()
    private var operator_button_clicked :Boolean = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button_0.setOnClickListener(this)
        button_1.setOnClickListener(this)
        button_2.setOnClickListener(this)
        button_3.setOnClickListener(this)
        button_4.setOnClickListener(this)
        button_5.setOnClickListener(this)
        button_6.setOnClickListener(this)
        button_7.setOnClickListener(this)
        button_8.setOnClickListener(this)
        button_9.setOnClickListener(this)
        plus_button.setOnClickListener(this)
        minus_button.setOnClickListener(this)
        equal_button.setOnClickListener(this)
        mutiply_button.setOnClickListener(this)
        divide_button.setOnClickListener(this)
        empty_button.setOnClickListener(this)
        delete_button.setOnClickListener(this)
        information_button.setOnClickListener(this)
        decimal_button.setOnClickListener(this)
        Log.d("test", "Start")
    }

    override fun onClick(v: View?) {
        when (v) {
            button_0 -> click_operandButton("0")
            button_1 -> click_operandButton("1")
            button_2 -> click_operandButton("2")
            button_3 -> click_operandButton("3")
            button_4 -> click_operandButton("4")
            button_5 -> click_operandButton("5")
            button_6 -> click_operandButton("6")
            button_7 -> click_operandButton("7")
            button_8 -> click_operandButton("8")
            button_9 -> click_operandButton("9")
            minus_button -> click_operatorButton("-")
            plus_button -> click_operatorButton("+")
            mutiply_button -> click_operatorButton("*")
            divide_button -> click_operatorButton("/")
            decimal_button -> click_operatorButton(".")
            equal_button -> {
                textView.setText(calculating().toString())
                cal.operandListClear()
                cal.operatorListClear()
            }
            empty_button -> textView.setText("")
            delete_button -> deleteChar()
            information_button -> showinformation()
        }
    }
    fun click_operandButton(operand:String){
        textView.append(operand)
        operator_button_clicked = false
    }
    fun click_operatorButton(operator:String){
        if(operator_button_clicked)
            return
        else {
            textView.append(operator)
            operator_button_clicked = true
        }
    }
    fun showinformation() {
        Log.d("test", "Show information")
        val intent = Intent(this, information::class.java)
        startActivity(intent)
    }

    fun deleteChar() {
        var temp: String = textView.text.toString()
        temp = temp.slice(0..temp.length - 2)
        textView.setText(temp)
    }
    fun calculating(): Double {                     //转换为后缀表达式后进行计算
        var i = 0;
        var j = 0;
        var result: Double = 0.0
        var expression = textView.text.toString()
        var operandList = expression.split(Regex("[-,+,*,/,%]+")) as MutableList<String>
        var operatorList = mutableListOf<String>()
        for (x in 0..expression.length - 1) {
            if (cal.isOperator(expression[x].toString()))
                operatorList.add(expression[x].toString())
        }
        /*  var List = mutableListOf<String>()
       for(y in 0..operandList.size-1){
           List.add(operandList[y])
           if (y < operatorList.size)
               List.add(operatorList[y])
       }*/
        while (i < operandList.size) {
            cal.addList(operandList[i])
            if (j < operatorList.size) {
                if (cal.operatorListisEmpty())
                    cal.addoperator(operatorList[j])
                else
                    cal.putOperator(operatorList[j])
            }
            i++
            j++
        }
        while (cal.getOperatorList().size > 0) {
            cal.addList(cal.getCurrentOperator())
            cal.deleteCurrentOperator()
        }
        Log.d("test",cal.getList().toString())
        i = 0
        var temp: Double = 0.0
        while (i < cal.getListSize()) {
            if (!cal.isOperator(cal.getListposition(i)))
                cal.addoperand(cal.getListposition(i++))
            else {
                temp = cal.chooce_calculation(cal.getListposition(i), cal.getCurrentSecondOperand().toDouble(), cal.getCurrentOperand().toDouble())
                cal.deleteTwoOperand()
                cal.addoperand(temp.toString())
                i++
            }
        }

        return cal.getCurrentOperand().toDouble()
    }
}