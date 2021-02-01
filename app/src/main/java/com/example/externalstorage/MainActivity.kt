package com.example.externalstorage

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.io.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editTextFileName: EditText=findViewById(R.id.editText1)
        val editTextData:EditText=findViewById(R.id.editText2)
        val saveButton: Button=findViewById(R.id.button1)
        val readButton:Button=findViewById(R.id.button2)

        saveButton.setOnClickListener {
            val filename = editTextFileName.text.toString()
            val data = editTextData.text.toString()
            var fos: FileOutputStream
            try {
                val myFile = File("/Download/$filename")
                myFile.createNewFile()
                val fOut = FileOutputStream(myFile)
                val myOutWriter = OutputStreamWriter(fOut)
                myOutWriter.append(data)
                myOutWriter.close()
                fOut.close()
                Toast.makeText(applicationContext, filename + "saved", Toast.LENGTH_LONG).show()
            } catch (e: FileNotFoundException) {
                e.printStackTrace()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }

        readButton.setOnClickListener {
            val filename = editTextFileName.text.toString()
            val stringBuffer = StringBuffer()
            var aDataRow: String
            var aBuffer = ""
            try {
                val myFile = File("/Download/$filename")
                val fIn = FileInputStream(myFile)
                val myReader = BufferedReader(
                    InputStreamReader(fIn)
                )
                while (myReader.readLine().also { aDataRow = it } != null) {
                    aBuffer += """
                        $aDataRow
                        
                        """.trimIndent()
                }
                myReader.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
            Toast.makeText(applicationContext, aBuffer, Toast.LENGTH_LONG).show()
        }
    }
}