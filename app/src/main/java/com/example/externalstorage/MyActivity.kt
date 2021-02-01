package com.example.externalstorage

import android.os.Bundle
import android.os.Environment
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException

/*class MyActivity : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mine)
        val editText: EditText = findViewById(R.id.edt)
        val write: Button = findViewById(R.id.Output)
        val read: Button = findViewById(R.id.Input)
        val textView: TextView = findViewById(R.id.textView)
        val resolver = this.contentResolver
        val contentValues = ContentValues().apply {
            put(MediaStore.MediaColumns.DISPLAY_NAME, "myDoc1")
            put(MediaStore.MediaColumns.MIME_TYPE, "text/plain")
            put(MediaStore.MediaColumns.RELATIVE_PATH, "Documents")
        }
        val uri: Uri? = resolver.insert(MediaStore.Files.getContentUri("external"), contentValues)
        Log.d("Uri", "$uri")

        write.setOnClickListener {
            val edt : String = editText.text.toString()
            if (uri != null) {
                resolver.openOutputStream(uri).use {
                    it?.write("Ritika Gyanani $edt".toByteArray())
                    it?.close()

                }
            }
        }
        read.setOnClickListener {
            if (uri != null) {
                resolver.openInputStream(uri).use {
                    val data = ByteArray(50)
                    it?.read(data)
                    textView.text = String(data)

                }
            }
        }
    }
}*/


class MyActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mine)
        val textView: TextView = findViewById(R.id.textView)
        val myText: String = textView.text.toString()
        val file = File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS), "abc.txt")

        val fileName = "abc.txt"
        val folder = Environment.getExternalStorageDirectory()
        val myFile = File(folder, fileName)
        val fStream = FileInputStream(myFile)
        val sBuffer = StringBuffer()
        var i: Int
        while (fStream.read().also { i = it } != -1) {
            sBuffer.append(i.toChar())
        }
        fStream.close()
        val details = sBuffer.toString()
        textView.text = details
        writeTextData(myFile,details)

        if (!myFile.exists()) {
            try {
                myFile.createNewFile()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun writeTextData(file: File, data: String) {
        val fileOutputStream: FileOutputStream? = null
        try {
            fileOutputStream?.write(data.toByteArray())

            Toast.makeText(this, "Done" + file.absolutePath, Toast.LENGTH_SHORT).show()
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        } finally {
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close()
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
        }
    }
}