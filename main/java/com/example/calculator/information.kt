package com.example.calculator

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_information.*
import java.io.File
import java.io.FileInputStream

class information : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_information)
       /* val fileName = "Version_information"
        val file = File(fileName)
        val fileInputStream = FileInputStream(file)
        val bytes = byteArrayOf()
        while (fileInputStream.read(bytes) !== -1) {
            val content = String(bytes)
            informationTextView.append(content)
        }
        fileInputStream.close()*/
    }
}