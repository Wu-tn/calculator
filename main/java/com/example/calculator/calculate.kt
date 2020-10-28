package com.example.calculator

import java.util.*

class calculate {
    private  var operandList = mutableListOf<String>()
    private var List = mutableListOf<String>()        //保存后缀表达式
    private var operatorList = mutableListOf<String>()
    private var temp:Double = 0.0
    fun addList(temp:String){
        List.add(temp)
    }
    fun addoperand(operand:String){
        operandList.add(operand)
    }
    fun addoperator(operator:String){
        operatorList.add(operator)
    }
    fun plus(operand1:Double ,operand2:Double): Double {
        return (operand1 + operand2)
    }
    fun mutiply(operand1:Double ,operand2:Double) :Double{
        return (operand1 * operand2)
    }
    fun minus(operand1:Double ,operand2:Double): Double {
        return (operand1 - operand2)
    }
    fun divide(operand1:Double ,operand2:Double): Double {
        return (operand1 / operand2)
    }
    fun chooce_calculation(operator: String, operand1: Double, operand2: Double): Double{
        var temp:Double= 0.0
        when(operator){
            "+" -> temp = plus(operand1,operand2)
            "-" -> temp =  minus(operand1,operand2)
            "*" -> temp =  mutiply(operand1,operand2)
            "/" -> temp =  divide(operand1,operand2)
        }
        return temp
    }
    fun getCurrentOperator(): String {
        return operatorList[operatorList.size-1]
    }
    fun compareOperator(operator1: String,operator2:String): Int{
        val highlevel = LinkedList<String>(listOf("*", "/", "%"))
        val lowlevel = LinkedList<String>(listOf("-", "+"))
        if(operator1 in highlevel&&operator2 in lowlevel)
            return 1
        else if(operator2 in highlevel&&operator1 in lowlevel)
            return -1
        else
            return 0
    }
    fun getCurrentOperand():String = operandList[operandList.size-1]
    fun getCurrentSecondOperand():String = operandList[operandList.size-2]
    fun deleteCurrentOperator(){
        operatorList.removeAt(operatorList.size-1)
    }
    fun deleteTwoOperand(){
        repeat(2) {
            operandList.removeAt(operandList.size-1)
        }
    }
    fun deleteCurrentOperand(){
        operandList.removeAt(operandList.size-1)
    }
    fun setTemp(temp:Double) {
        this.temp += temp
    }
    fun getTemp() = this.temp
    fun isOperator(operator:String): Boolean {
        val operators = LinkedList<String>(listOf("*", "/", "%","+","-"))
        if(operator in operators)
            return true
        else
            return false
    }
    fun getOperandList() = operandList
    fun getOperatorList() = operatorList
    fun operandListClear(){
        this.operandList.clear()
    }
    fun operatorListClear(){
        this.operatorList.clear()
    }
    fun operatorListisEmpty(): Boolean {
        if(this.operatorList.size==0)
            return true
        else
            return false
    }
    fun putOperator(operator:String){
        while(!operatorListisEmpty()){
            if(compareOperator(operator,getCurrentOperator())!=1){
                var operator = getCurrentOperator()
                addList(operator)
                deleteCurrentOperator()
            }
            else break
        }
        addoperator(operator)
    }
    fun getListSize() = List.size
    fun getListposition(position:Int) = List[position]
    fun getList() = List
}