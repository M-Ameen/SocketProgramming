package com.example.socketprogramming


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import com.example.socketprogramming.databinding.ActivityMainBinding
import java.io.IOException
import java.io.PrintWriter
import java.net.Socket

class MainActivity : AppCompatActivity() {
    private lateinit var client: Socket
    private lateinit var printwriter: PrintWriter
    private lateinit var textField: EditText
    private lateinit var button: Button
    private lateinit var message: String
private lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        textField = binding.editText1
        button = binding.button1

        button.setOnClickListener {
            message = textField.text.toString()
            Thread(ClientThread(message)).start()
        }
    }

    inner class ClientThread(private val message: String) : Runnable {
        override fun run() {
            try {
                client = Socket("192.168.43.15", 4444)
                printwriter = PrintWriter(client.getOutputStream(), true)
                printwriter.write(message)
                printwriter.flush()
                printwriter.close()
                client.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }

            runOnUiThread {
                textField.setText("")
            }
        }
    }
}
